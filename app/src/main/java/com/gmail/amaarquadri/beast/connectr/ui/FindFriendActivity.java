package com.gmail.amaarquadri.beast.connectr.ui;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.gmail.amaarquadri.beast.connectr.R;
import com.gmail.amaarquadri.beast.connectr.logic.Friend;
import com.gmail.amaarquadri.beast.connectr.logic.LocationData;
import com.gmail.amaarquadri.beast.connectr.logic.ServerAsync;
import com.gmail.amaarquadri.beast.connectr.logic.ServerRequest;
import com.gmail.amaarquadri.beast.connectr.logic.User;

/**
 * Created by amaar on 2018-01-27.
 * Shows the direction of the desired friend.
 */
public class FindFriendActivity extends Activity implements SensorEventListener {
    private User user;
    private Friend friend;
    private ImageView arrowImageView;
    private Location userLocation;
    private Location friendLocation;
    private SensorManager mSensorManager;
    private float heading;
    private boolean userLocationUpdated;
    private boolean friendLocationUpdated;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_friend);

        arrowImageView = findViewById(R.id.arrowImage);
        user = (User) getIntent().getSerializableExtra("user");
        friend = (Friend) getIntent().getSerializableExtra("friend");
        heading = 0;
        userLocationUpdated = false;
        friendLocationUpdated = false;

        Context this_ = this;
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        new Thread(() -> {
            while (true) {
                PollLocationAsync.pollLocation(this_, location -> {
                    synchronized (this_) {
                        userLocation = location;
                        userLocationUpdated = true;
                    }
                });
                ServerAsync.sendToServer(ServerRequest.createGetLocationServerRequest(user, friend), (response) -> {
                    synchronized (this_) {
                        friendLocation = toLocation(response.getLocationData());
                        friendLocationUpdated = true;
                    }
                });

                while (userLocationUpdated && friendLocationUpdated) {}
                handleEverything();
                userLocationUpdated = false;
                friendLocationUpdated = false;

                try {
                    Thread.sleep(20000);
                } catch (InterruptedException e) {}
            }
        }).start();
    }

    private Location toLocation(LocationData locationData) {
        //TODO: figure out what to put as provider String
        Location result = new Location("Database");
        result.setLongitude(locationData.getLongitude());
        result.setLatitude(locationData.getLatitude());
        result.setTime(locationData.getLastUpdateUnixTime());
        return result;
    }

    private synchronized void handleEverything() {
        float bearing = userLocation.bearingTo(friendLocation);
        float angle = heading - bearing;
        while (angle < 0) angle += 360;
        while (angle > 360) angle -= 360;
        arrowImageView.setRotation(angle);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        heading = event.values[0];
        handleEverything();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
}


