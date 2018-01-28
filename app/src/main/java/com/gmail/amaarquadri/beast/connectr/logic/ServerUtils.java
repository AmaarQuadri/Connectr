package com.gmail.amaarquadri.beast.connectr.logic;

import android.support.annotation.NonNull;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Base64;

/**
 * Created by amaar on 2018-01-27.
 */

public class ServerUtils {
    @NonNull
    public static ServerResponse sendToServer(ServerRequest serverRequest) throws IOException {
        String out = serializeServerRequest(serverRequest);
        //send data
        //get back data
        String in = ;
        return deserializeServerResponse(in);
    }

    private static ServerResponse deserializeServerResponse(String serverResponse) throws IOException, ClassNotFoundException {
        ObjectInputStream inputStream = new ObjectInputStream(new ByteArrayInputStream(Base64.getDecoder().decode(serverResponse)));
        ServerResponse response = (ServerResponse) inputStream.readObject();
        inputStream.close();
        return response;
    }

    private static String serializeServerRequest(ServerRequest serverRequest) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(serverRequest);
        objectOutputStream.close();
        return Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());
    }
}
