package com.gmail.amaarquadri.beast.connectr;

import android.location.Location;

import java.util.ArrayList;

/**
 * Created by amaar on 2018-01-27.
 */

public class User {
    private final int index;
    private final int id;
    private final String username;
    private final String password;
    private final Location lastLocation;
    private final ArrayList<User> friends;

    public User(int index, int id, String username, String password, Location lastLocation, ArrayList<User> friends) {
        this.index = index;
        this.id = id;
        this.username = username;
        this.password = password;
        this.lastLocation = lastLocation;
        this.friends = friends;
    }
}
