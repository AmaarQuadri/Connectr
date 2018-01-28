package com.gmail.amaarquadri.beast.connectr.logic;

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

public class ServerUtils {
    private static Socket socket;
    private static PrintWriter out;
    private static BufferedReader in;


    @NonNull
    public static ServerResponse sendToServer(ServerRequest serverRequest) throws IOException, ClassNotFoundException {
        String output = serializeServerRequest(serverRequest);
        initializeConnection();

        out.println(output);
        //send data


        String input="";
        //get back data
        try {
            input = in.readLine();
        }catch (IOException e){
            //output read fail error.
            //system.exit(1)
        }

        return deserializeServerResponse(input);
    }

    private static void initializeConnection(){
        try{
            socket = new Socket("LAPTOP-0MBJPDGI", 4321);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }catch(UnknownHostException e){
            //output an unknown host error
            //system.exit(1);
        }catch(IOException e){
            //output an IO error
            //system.exit(1);
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
