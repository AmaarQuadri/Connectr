package com.gmail.amaarquadri.beast.connectr;

import java.io.Serializable;

/**
 * Created by amaar on 2018-01-27.
 */

public class LocationPermission implements Serializable {
    private final int index;
    private final int id;
    private final String username;
    private final boolean expires;
    private final long expirationUnixTime;

    public LocationPermission(int index, int id, String username, boolean expires, long expirationUnixTime) {
        this.index = index;
        this.id = id;
        this.username = username;
        this.expires = expires;
        this.expirationUnixTime = expirationUnixTime;
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

    public boolean isExpires() {
        return expires;
    }

    public long getExpirationUnixTime() {
        return expirationUnixTime;
    }
}
