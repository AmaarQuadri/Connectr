package com.gmail.amaarquadri.beast.connectr;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ListAdapter;

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

        for (User friend : user.getFriends()) {
            LinearLayout friendRow = new LinearLayout(this);
            friendRow.setOrientation(LinearLayout.HORIZONTAL);

        }
    }
}
