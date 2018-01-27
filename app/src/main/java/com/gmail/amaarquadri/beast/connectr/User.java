package com.gmail.amaarquadri.beast.connectr;

import java.io.Serializable;
import java.util.ArrayList;

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
    private final ArrayList<User> friends;
    private final ArrayList<LocationPermission> locationPermissions;

    public User(int index, int id, String username, String password, LocationData lastLocation,
                ArrayList<User> friends, ArrayList<LocationPermission> locationPermissions) {
        this.index = index;
        this.id = id;
        this.username = username;
        this.password = password;
        this.lastLocationData = lastLocation;
        this.friends = friends;
        this.locationPermissions = locationPermissions;
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

    public ArrayList<User> getFriends() {
        return friends;
    }

    public ArrayList<LocationPermission> getLocationPermissions() {
        return locationPermissions;
    }
}
