package com.gmail.amaarquadri.beast.connectr;

import java.io.Serializable;

/**
 * Created by amaar on 2018-01-27.
 */

public class LocationPermission implements Serializable {
    private final int index;
    private final int id;
    private final String username;

    public LocationPermission(int index, int id, String username) {
        this.index = index;
        this.id = id;
        this.username = username;
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
}
