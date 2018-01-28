package com.gmail.amaarquadri.beast.connectr.ui;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by amaar on 2018-01-27.
 */

public class UpdateLocationService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private Location getLocation() {
        return null;
    }
}
