package com.gmail.amaarquadri.beast.connectr.logic;

import java.io.Serializable;

/**
 * Created by amaar on 2018-01-27.
 */

public class ServerResponse implements Serializable {
    public enum Type {
        LOGIN_RESULT, LOGIN_FAILED, ADD_FRIEND_SUCCESS, ADD_FRIEND_FAILED
    }

    private final Type type;
    private final User user;
    private final Friend newFriend;

    public ServerResponse(Type type) {
        this.type = type;
        user = null;
        newFriend = null;
    }

    public ServerResponse(Type type, User user) {
        this.type = type;
        this.user = user;
    }



    public Type getType() {
        return type;
    }

    public User getUser() {
        return user;
    }
}
