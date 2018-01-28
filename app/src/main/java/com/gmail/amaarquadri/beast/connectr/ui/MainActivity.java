package com.gmail.amaarquadri.beast.connectr.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.gmail.amaarquadri.beast.connectr.R;
import com.gmail.amaarquadri.beast.connectr.logic.Friend;
import com.gmail.amaarquadri.beast.connectr.logic.User;

/**
 * Created by amaar on 2018-01-27.
 */

public class MainActivity extends Activity {
    private User user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        user = (User) getIntent().getSerializableExtra("user");

        boolean allFriendsHavePermission = true;
        boolean noFriendsHavePermission = true;
        for (Friend friend : user.getFriends()) {
            if (friend.hasPermission()) noFriendsHavePermission = false;
            else allFriendsHavePermission = false;
        }

        Button friendsButton = findViewById(R.id.friends_button);
        if (allFriendsHavePermission) friendsButton.setText("All Friends");
        else if (noFriendsHavePermission) friendsButton.setText("Nobody");
        else friendsButton.setText("SomeFriends");
    }

    public void addFriend(View view) {
        Intent intent = new Intent(this, AddFriendActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    public void friendsPage(View view) {
        Intent intent = new Intent(this, FriendsPermissionActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    public void findFriend(View view) {
        Intent intent = new Intent(this, FindFriendActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    public void logout(View view) {
        startActivity(new Intent(this, LoginActivity.class));
    }
}
