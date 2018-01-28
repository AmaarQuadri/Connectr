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
    public static ServerResponse sendToServer(ServerRequest serverRequest) {
        return null;
    }

    private static ServerRequest deserializeServerRequest(String serverRequest) throws IOException, ClassNotFoundException {
        ObjectInputStream inputStream = new ObjectInputStream(new ByteArrayInputStream(Base64.getDecoder().decode(serverRequest)));
        ServerRequest request  = (ServerRequest) inputStream.readObject();
        inputStream.close();
        return request;
    }

    private static String serializeServerResponse(ServerResponse serverResponse) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(serverResponse);
        objectOutputStream.close();
        return Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());
    }
}
