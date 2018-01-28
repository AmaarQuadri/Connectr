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

import java.io.IOException;

/**
 * Created by amaar on 2018-01-27.
 * Activity to add a new friend.
 */
public class AddFriendActivity extends Activity {
    private EditText usernameEditText;
    private User user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_friend);
        usernameEditText = findViewById(R.id.username);

        user = (User) getIntent().getSerializableExtra("user");
    }

    public void addFriend(View view) {
        ServerResponse response;
        try {
            response = ServerUtils.sendToServer(ServerRequest.createAddFriendServerRequest(user,
                    usernameEditText.getText().toString()));
        } catch (IOException | ClassNotFoundException e) {
            Toast.makeText(this, "Cannot connect to Server.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (response.getType() == ServerResponse.Type.SUCCESS) {
            user.getFriends().add(response.getNewFriend());
            usernameEditText.getText().clear();
            Toast.makeText(this, "Friend added!", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "That user doesn't exist.", Toast.LENGTH_SHORT).show();
        }

    }
}
