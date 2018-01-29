package com.gmail.amaarquadri.beast.connectr.logic;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by amaar on 2018-01-28.
 */
public class ServerAsync {
    private static boolean connectionInitialized = false;
    private static ObjectOutputStream out;
    private static ObjectInputStream in;

    public interface Callback {
        void onFinish(ServerResponse serverResponse);
    }

    public static void sendToServer(ServerRequest serverRequest, Callback callback) {
        new Thread(() -> {
            try {
                if (!connectionInitialized) initializeConnection();
                out.writeObject(serverRequest);
                callback.onFinish((ServerResponse) in.readObject());
            }
            catch (IOException | ClassNotFoundException e) {
                callback.onFinish(null);
            }
        }).start();
    }

    private static void initializeConnection() throws IOException {
        try {
            Socket socket = new Socket("172.30.155.93", 4555); //DESKTOP-8M8UDU4 172.30.155.93
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
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
}
