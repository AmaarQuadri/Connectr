package com.gmail.amaarquadri.beast.connectr.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.gmail.amaarquadri.beast.connectr.R;

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

    public void create(View view) {
        String myUserame = usernameEditText.getText().toString();
        String myEmail = emailEditText.getText().toString();
        String myPassword = passwordEditText.getText().toString();
        String myPasswordRepeat = reenterPasswordEditText.getText().toString();

        if (!myPassword.equals(myPasswordRepeat))
        {
            Toast.makeText(this, "Your passwords do not match! Please reenter your information", Toast.LENGTH_SHORT).show();
            passwordEditText.getText().clear();
            reenterPasswordEditText.getText().clear();
        }


    }
}
