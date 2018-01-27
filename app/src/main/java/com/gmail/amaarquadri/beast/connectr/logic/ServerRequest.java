package com.gmail.amaarquadri.beast.connectr.logic;

import java.io.Serializable;

/**
 * Created by amaar on 2018-01-27.
 */

public class ServerRequest implements Serializable {
    public enum Type {
        LOGIN;
    }

    private final Type type;
    private final String username;
    private final String password;

    public ServerRequest(String username, String password) {
        this.type = Type.LOGIN;
        this.username = username;
        this.password = password;
    }
}
