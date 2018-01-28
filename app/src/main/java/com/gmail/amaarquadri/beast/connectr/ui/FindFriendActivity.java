package com.gmail.amaarquadri.beast.connectr.ui;

import android.app.Activity;
import android.content.Context;
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_friend);

        arrowImageView = findViewById(R.id.arrowImage);
        user = (User) getIntent().getSerializableExtra("user");
        friend = (Friend) getIntent().getSerializableExtra("friend");

        Context this_ = this;
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
            /* protected void onMeasure(int widthSpecId, int heightSpecId) {
                Log.e(TAG, "onMeasure" + widthSpecId);
                setMeasuredDimension(SCREEN_WIDTH, SCREEN_HEIGHT -
                        game.findViewById(R.id.flag).getHeight());
            }
            while (userLocation == null && friendLocation == null) {}
            synchronized (this_) {
                float bearing = userLocation.bearingTo(friendLocation);
                int centerx = ImageView.ge/2;
                int centery = height/2;
                canvas.drawLine(centerx, 0, centerx, height, paint);
                canvas.drawLine(0, centery, width, centery, paint);
                // Rotate the canvas with the azimut
                if (azimut != null)
                    canvas.rotate(-azimut*360/(2*3.14159f), centerx, centery);
                paint.setColor(0xff0000ff);
                arrowImageView.setRotation(bearing); 

            }*/
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

