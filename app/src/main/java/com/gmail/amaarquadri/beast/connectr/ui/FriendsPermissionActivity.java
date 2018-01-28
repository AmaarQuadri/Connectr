package com.gmail.amaarquadri.beast.connectr.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.gmail.amaarquadri.beast.connectr.R;
import com.gmail.amaarquadri.beast.connectr.logic.Friend;
import com.gmail.amaarquadri.beast.connectr.logic.ServerRequest;
import com.gmail.amaarquadri.beast.connectr.logic.ServerResponse;
import com.gmail.amaarquadri.beast.connectr.logic.ServerUtils;
import com.gmail.amaarquadri.beast.connectr.logic.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by amandamorin on 2018-01-27.
 */

public class FriendsPermissionActivity extends Activity {
    private LinearLayout friendsLinearLayout;
    private Switch allFriendsSwitch;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = (User) getIntent().getSerializableExtra("user");
        friendsLinearLayout = findViewById(R.id.friends_list);
        allFriendsSwitch = findViewById(R.id.all_friends_switch);

        ArrayList<Friend> friends = user.getFriends();
        if (friends.isEmpty()) return;
        friends.sort(Comparator.comparing(Friend::getUsername));

        boolean allFriendsHavePermission = true;
        for (Friend friend : user.getFriends()) {
            LinearLayout friendRow = new LinearLayout(this);
            friendRow.setOrientation(LinearLayout.HORIZONTAL);

            TextView friendNameTextView = new TextView(this);
            friendNameTextView.setText(friend.getUsername());
            friendNameTextView.setTextSize(16);
            //TODO: make sure set color works
            friendNameTextView.setTextColor(0);
            friendRow.addView(friendNameTextView);

            ToggleButton button = new ToggleButton(this);
            if (friend.friendHasPermission()) button.setSelected(true);
            else allFriendsHavePermission = false;
            button.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    ServerResponse response;
                    try {
                        response = ServerUtils.sendToServer(ServerRequest.createEnablePermissionServerRequest(user, friend));
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                        //TODO: handle
                        return;
                    }
                    if (response.getType() == ServerResponse.Type.ENABLE_PERMISSION_FAILED) {
                        //TODO: handle
                        return;
                    }
                }
                else {
                    ServerResponse response;
                    try {
                        response = ServerUtils.sendToServer(ServerRequest.createDisablePermissionServerRequest(user, friend));
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                        //TODO: handle
                        return;
                    }
                    if (response.getType() == ServerResponse.Type.DISABLE_PERMISSION_FAILED) {
                        //TODO: handle
                        return;
                    }
                }
            });
            friendRow.addView(button);
        }

        if (allFriendsHavePermission) allFriendsSwitch.setChecked(true);
    }

    public void addFriend(View view) {
        Intent intent = new Intent(this, AddFriendActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }
}
