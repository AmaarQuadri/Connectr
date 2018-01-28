package com.gmail.amaarquadri.beast.connectr.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.gmail.amaarquadri.beast.connectr.R;
import com.gmail.amaarquadri.beast.connectr.logic.ServerAsync;
import com.gmail.amaarquadri.beast.connectr.logic.ServerRequest;
import com.gmail.amaarquadri.beast.connectr.logic.ServerResponse;

/**
 * Created by amaar on 2018-01-27.
 */
public class SignupActivity extends Activity {
    private EditText usernameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText reenterPasswordEditText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        emailEditText = findViewById(R.id.email);
        usernameEditText = findViewById(R.id.create_username);
        passwordEditText = findViewById(R.id.create_password);
        reenterPasswordEditText = findViewById(R.id.create_password_repeat);
    }

    public void createAccount(View view) {
        String username = usernameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String passwordRepeat = reenterPasswordEditText.getText().toString();

        if (!password.equals(passwordRepeat)) {
            Toast.makeText(this, "Your passwords do not match! Please reenter your information", Toast.LENGTH_SHORT).show();
            passwordEditText.getText().clear();
            reenterPasswordEditText.getText().clear();
            return;
        }

        if (username.isEmpty() || password.isEmpty() || passwordRepeat.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "All fields must have entries",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        ServerAsync.sendToServer(ServerRequest.createCreateAccountServerRequest(username, password), (response) -> {
            if (response.getType() == ServerResponse.Type.FAILED) {
                Toast.makeText(this, "That username is already taken, sorry!",
                        Toast.LENGTH_SHORT).show();
                usernameEditText.getText().clear();
                passwordEditText.getText().clear();
                reenterPasswordEditText.getText().clear();
            }
            else {
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("user", response.getUser());
                startActivity(intent);
            }
        });
    }
}
