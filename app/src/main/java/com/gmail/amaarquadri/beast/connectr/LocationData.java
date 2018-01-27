package com.gmail.amaarquadri.beast.connectr;

import android.location.Location;

/**
 * Created by amaar on 2018-01-27.
 */

public class LocationData {
    private final double latitude;
    private final double longitude;

    public LocationData(Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();
    }
}
