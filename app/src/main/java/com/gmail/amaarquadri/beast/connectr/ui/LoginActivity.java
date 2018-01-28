package com.gmail.amaarquadri.beast.connectr.ui;

import android.app.Activity;
import android.content.Intent;
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
 * Activty to login to a User account.
 */
public class LoginActivity extends Activity {
    private EditText usernameEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
    }

    public void login(View view) {
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if (username.isEmpty() && password.isEmpty()) {
            Toast.makeText(this, "Username and Password cannot be empty.",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        else if (username.isEmpty()) {
            Toast.makeText(this, "Username cannot be empty.", Toast.LENGTH_SHORT).show();
            return;
        }
        else if (password.isEmpty()) {
            Toast.makeText(this, "Password cannot be empty.", Toast.LENGTH_SHORT).show();
            return;
        }

        ServerResponse response;
        try {
            response = ServerUtils.sendToServer(ServerRequest.createLoginServerRequest(username, password));
        } catch (IOException | ClassNotFoundException e) {
            Toast.makeText(this, "Unable to connect to server.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (response.getType() == ServerResponse.Type.LOGIN_FAILED) {
            Toast.makeText(this, "Login failed. Please try again or make a new account.",
                    Toast.LENGTH_LONG).show();
        }

        User user = response.getUser();
        Intent intent = new Intent(this, FriendsPermissionActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }
}
