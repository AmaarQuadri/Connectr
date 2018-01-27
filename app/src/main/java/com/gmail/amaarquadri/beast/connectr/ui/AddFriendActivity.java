package com.gmail.amaarquadri.beast.connectr.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;

import com.gmail.amaarquadri.beast.connectr.R;

/**
 * Created by amaar on 2018-01-27.
 */

public class AddFriendActivity extends Activity {
    private EditText usernameEditText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        usernameEditText = findViewById(R.id.username);
    }

    public void addFriend(View view) {

    }
}
