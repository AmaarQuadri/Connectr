package com.gmail.amaarquadri.beast.connectr.ui;

import android.app.Activity;
import android.content.Context;
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
public class FindFriendActivity extends Activity {
    private User user;
    private Friend friend;
    private ImageView arrowImageView;
    private Location userLocation;
    private Location friendLocation;
    private SensorManager mSensorManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_friend);

        arrowImageView = findViewById(R.id.arrowImage);
        user = (User) getIntent().getSerializableExtra("user");
        friend = (Friend) getIntent().getSerializableExtra("friend");

        Context this_ = this;
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        new Thread(() -> {
            PollLocationAsync.pollLocation(this_, location -> {
                synchronized (this_) {
                    userLocation = location;
                }
            });
            ServerAsync.sendToServer(ServerRequest.createGetLocationServerRequest(user, friend), (response) -> {
                synchronized (this_) {
                    friendLocation = toLocation(response.getLocationData());
                }
            });



            while (userLocation == null && friendLocation == null) {}
            synchronized (this_) {
                float initialBearing = userLocation.bearingTo(friendLocation);

                // Rotation matrix based on current readings from accelerometer and magnetometer.
                final float[] rotationMatrix = new float[9];
                mSensorManager.getRotationMatrix(rotationMatrix, null,
                        accelerometerReading, magnetometerReading);

                // Express the updated rotation matrix as three orientation angles.
                final float[] orientationAngles = new float[3];
                mSensorManager.getOrientation(rotationMatrix, orientationAngles);
                private SensorManager mSensorManager;
                private final float[] mAccelerometerReading = new float[3];
                private final float[] mMagnetometerReading = new float[3];

                private final float[] mRotationMatrix = new float[9];
                private final float[] mOrientationAngles = new float[3];
                arrowImageView.setRotation(bearing);

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
}


