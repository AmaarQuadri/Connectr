package com.gmail.amaarquadri.beast.connectr.logic;

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

/**
 * Created by amaar on 2018-01-28.
 */
public class ServerAsync {
    private static boolean connectionInitialized = false;
    private static PrintWriter out;
    private static BufferedReader in;

    public interface Callback {
        void onFinish(ServerResponse serverResponse);
    }

    public static void sendToServer(ServerRequest serverRequest, Callback callback) {
        new Thread(() -> {
            try {
                if (!connectionInitialized) initializeConnection();
                out.println(serializeServerRequest(serverRequest));

                String input = in.readLine();
                callback.onFinish(deserializeServerResponse(input));
            }
            catch (IOException | ClassNotFoundException e) {
                callback.onFinish(null);
            }
        }).start();
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
        try (ObjectInputStream inputStream = new ObjectInputStream(new ByteArrayInputStream(serverResponse.getBytes()))) {
            return (ServerResponse) inputStream.readObject();
        }
    }

    private static String serializeServerRequest(ServerRequest serverRequest) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream outputStream = new ObjectOutputStream(byteArrayOutputStream);
        outputStream.writeObject(serverRequest);
        outputStream.close();
        return byteArrayOutputStream.toString();
    }
}
