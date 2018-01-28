package com.gmail.amaarquadri.beast.connectr.logic;

import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Base64;

/**
 * Created by amaar on 2018-01-27.
 */
public class ServerAsyncTask extends AsyncTask<ServerRequest, Void, ServerResponse> {
    private static boolean connectionInitialized = false;
    private static PrintWriter out;
    private static BufferedReader in;

    @Override
    protected ServerResponse doInBackground(ServerRequest... serverRequests) {
        try {
            if (!connectionInitialized) initializeConnection();
            out.println(serializeServerRequest(serverRequests[0]));

            String input = in.readLine();

            return deserializeServerResponse(input);
        }
        catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }

    private static void initializeConnection() throws IOException {
        try {
            Socket socket = new Socket("LAPTOP-0MBJPDGI", 4321);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            connectionInitialized = true;
        }
        catch (UnknownHostException e) {
            e.printStackTrace();
            throw new IOException("Server not found", e);
        }
        catch(IOException e) {
            e.printStackTrace();
            throw new IOException("No IO connection", e);
        }
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
