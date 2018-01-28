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

            while (userLocation == null && friendLocation == null) {}
            synchronized (this_) {

            }

            float bearing = locUser.bearingTo(locFriend);
            arrowImageView.setRotation(bearing);
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

