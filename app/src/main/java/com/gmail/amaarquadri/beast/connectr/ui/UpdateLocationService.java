package com.gmail.amaarquadri.beast.connectr.ui;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.gmail.amaarquadri.beast.connectr.logic.LocationData;
import com.gmail.amaarquadri.beast.connectr.logic.ServerRequest;
import com.gmail.amaarquadri.beast.connectr.logic.ServerResponse;
import com.gmail.amaarquadri.beast.connectr.logic.ServerUtils;
import com.gmail.amaarquadri.beast.connectr.logic.User;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import java.io.IOException;
import java.util.concurrent.Executor;

/**
 * Created by amaar on 2018-01-27.
 * Background service that updates my location as needed.
 */
public class UpdateLocationService extends IntentService {
    private FusedLocationProviderClient locationClient;
    private User user;

    public UpdateLocationService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (locationClient == null) locationClient = LocationServices.getFusedLocationProviderClient(this);
        user = (User) intent.getSerializableExtra("user");
        while (true) {
            try {
                Thread.sleep(20000);
            } catch (InterruptedException ignore) {}
            updateLocation();
        }
    }

    private void updateLocation() {
        try {
            locationClient.getLastLocation().addOnSuccessListener((Executor) this, location -> {
                if (location != null) {
                    LocationData locationData = new LocationData(location.getLatitude(), location.getLongitude(), location.getTime());
                    ServerResponse response;
                    try {
                        response = ServerUtils.sendToServer(ServerRequest.createUpdateLocationServerRequest(user, locationData));
                    } catch (IOException | ClassNotFoundException e) {
                        //TODO: handle
                        return;
                    }
                    if (response.getType() == ServerResponse.Type.FAILED) {
                        //TODO: handle
                    }
                }
                else {
                    //TODO: something?
                }
            });
        } catch(SecurityException e){
            //TODO: implement
            //ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION});
        }
    }
}
