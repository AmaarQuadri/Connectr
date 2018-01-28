package com.gmail.amaarquadri.beast.connectr.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gmail.amaarquadri.beast.connectr.R;
import com.gmail.amaarquadri.beast.connectr.logic.Friend;
import com.gmail.amaarquadri.beast.connectr.logic.User;

/**
 * Created by amandamorin on 2018-01-27.
 */
public class FindFriendListActivity extends Activity {
    private LinearLayout sharingListLinearLayout;
    private User user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_friend_list);

        sharingListLinearLayout = findViewById(R.id.sharing_list);
        user = (User) getIntent().getSerializableExtra("user");

        final Intent intent = new Intent(this, FindFriendActivity.class);
        intent.putExtra("user", user);
        for (Friend friend : user.getFriends()) if (friend.iHavePermission()) {
            LinearLayout friendRow = new LinearLayout(this);
            friendRow.setOrientation(LinearLayout.HORIZONTAL);

            TextView textView = new TextView(this);
            textView.setText(friend.getUsername());
            textView.setTextSize(20);
            friendRow.addView(textView);

            Button button = new Button(this);
            button.setText("Find");
            button.setOnClickListener(view -> {
                intent.putExtra("friend", friend);
                startActivity(intent);
            });
            friendRow.addView(button);
        }
    }

    public void requestLocation(View view) {
        Intent intent = new Intent(this, FriendsPermissionActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }
}
