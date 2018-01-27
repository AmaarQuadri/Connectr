package com.gmail.amaarquadri.beast.connectr;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by amaar on 2018-01-27.
 */

public class User implements Serializable {
    private static final long serialVersionUID = 1;

    private final int index;
    private final int id;
    private final String username;
    private final String password;
    private final LocationData lastLocationData;
    private final HashMap<User, Boolean> friendsMap;

    public User(int index, int id, String username, String password, LocationData lastLocation,
                HashMap<User, Boolean> friendsMap) {
        this.index = index;
        this.id = id;
        this.username = username;
        this.password = password;
        this.lastLocationData = lastLocation;
        this.friendsMap = friendsMap;
    }

    public int getIndex() {
        return index;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public LocationData getLastLocationData() {
        return lastLocationData;
    }

    public HashMap<User, Boolean> getFriendsMap() {
        return friendsMap;
    }

    public Set<User> getFriends() {
        return friendsMap.keySet();
    }
}
