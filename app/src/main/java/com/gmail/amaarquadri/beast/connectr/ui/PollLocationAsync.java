package com.gmail.amaarquadri.beast.connectr.ui;

import android.content.Context;
import android.location.Location;

import com.google.android.gms.location.LocationServices;

import java.util.concurrent.Executor;

/**
 * Created by amaar on 2018-01-27.
 * Background service that updates my location as needed.
 */
public class PollLocationAsync {
    public interface Callback {
        void onFinish(Location location);
    }

    public static void pollLocation(Context context, Callback callback) {
        new Thread(() -> {
            try {
                LocationServices.getFusedLocationProviderClient(context).getLastLocation()
                        .addOnSuccessListener((Executor) context, callback::onFinish);
            }
            catch (SecurityException e) {
                //TODO: implement dynamic permissions
                callback.onFinish(null);
            }
        }).start();
    }
}
