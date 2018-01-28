package com.gmail.amaarquadri.beast.connectr.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.gmail.amaarquadri.beast.connectr.R;
import com.gmail.amaarquadri.beast.connectr.logic.Friend;
import com.gmail.amaarquadri.beast.connectr.logic.User;

/**
 * Created by amaar on 2018-01-27.
 */

public class FindFriendActivity extends Activity {
    private User user;
    private Friend friend;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_friend);

        user = (User) getIntent().getSerializableExtra("user");
        friend = (Friend) getIntent().getSerializableExtra("friend");

        //FindFriendActivity = LocationServices.getFusedLocationProviderClient(this);
    }
}
