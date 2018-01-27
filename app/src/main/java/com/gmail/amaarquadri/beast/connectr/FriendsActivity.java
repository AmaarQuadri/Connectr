package com.gmail.amaarquadri.beast.connectr;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by amandamorin on 2018-01-27.
 */

public class FriendsActivity extends Activity {
    private LinearLayout friendsLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        User user = (User) getIntent().getSerializableExtra("user");
        friendsLinearLayout = findViewById(R.id.friends_list);

        ArrayList<User> friends = user.getFriends();
        friends.sort(Comparator.comparing(User::getUsername));

        for (User friend : user.getFriends()) {
            LinearLayout friendRow = new LinearLayout(this);
            friendRow.setOrientation(LinearLayout.HORIZONTAL);

            TextView friendNameTextView = new TextView(this);
            friendNameTextView.setText(friend.getUsername());
            friendNameTextView.setTextSize(16);
            //TODO: make sure set color works
            friendNameTextView.setTextColor(0);
            friendRow.addView(friendNameTextView);

            ToggleButton button = new ToggleButton(this);
            //button.setSelected(user.getLocationPermissions().stream().map(locationPermission -> locationPermission.ge));
            button.setOnClickListener(view -> {

            });
            friendRow.addView(button);
        }
    }
}
