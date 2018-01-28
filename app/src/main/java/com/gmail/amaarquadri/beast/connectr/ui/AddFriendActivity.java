package com.gmail.amaarquadri.beast.connectr.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.gmail.amaarquadri.beast.connectr.R;
import com.gmail.amaarquadri.beast.connectr.logic.ServerRequest;
import com.gmail.amaarquadri.beast.connectr.logic.ServerResponse;
import com.gmail.amaarquadri.beast.connectr.logic.ServerUtils;
import com.gmail.amaarquadri.beast.connectr.logic.User;

/**
 * Created by amaar on 2018-01-27.
 */

public class AddFriendActivity extends Activity {
    private EditText usernameEditText;
    private User user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        usernameEditText = findViewById(R.id.username);

        user = (User) getIntent().getSerializableExtra("user");
    }

    public void addFriend(View view) {
        ServerResponse response = ServerUtils.sendToServer(ServerRequest.createAddFriendServerRequest(user,
                usernameEditText.getText().toString()));
        if (response.getType() == ServerResponse.Type.ADD_FRIEND_SUCCESS) {
            user.getFriends().add(response.getNewFriend());
            usernameEditText.getText().clear();
            Toast.makeText(this, "Friend added!", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "That user doesn't exist.", Toast.LENGTH_SHORT).show();
        }

    }
}
