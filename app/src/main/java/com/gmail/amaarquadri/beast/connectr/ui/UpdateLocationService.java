package com.gmail.amaarquadri.beast.connectr.ui;

import android.Manifest;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.concurrent.Executor;

/**
 * Created by amaar on 2018-01-27.
 */

public class UpdateLocationService extends Service {
    //
    private FusedLocationProviderClient mFusedLocationClient;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);


        return null;
    }

    private Location getLocation() {
    try {
        //if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED) {
          //  ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION});
        //}
        mFusedLocationClient.getLastLocation().addOnSuccessListener((Executor) this, new OnSuccessListener<Location>() {
            public void onSuccess(Location location){
                if(location != null){
                    //do the things with the location data
                }
            }
        });
    }catch(SecurityException e){
        //todo

    }
    return null;
    }
}
