package com.gmail.amaarquadri.beast.connectr;

import java.io.Serializable;

/**
 * Created by amaar on 2018-01-27.
 */

public class ServerResponse implements Serializable {
    public enum Type {
        LOGIN_RESULT, LOGIN_FAILED
    }

    private final Type type;
    private final User user;

    public ServerResponse() {
        type = Type.LOGIN_FAILED;
        user = null;
    }

    public ServerResponse(User user) {
        type = Type.LOGIN_RESULT;
        this.user = user;
    }

    public Type getType() {
        return type;
    }

    public User getUser() {
        return user;
    }
}
