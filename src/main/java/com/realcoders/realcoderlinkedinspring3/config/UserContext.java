package com.realcoders.realcoderlinkedinspring3.config;

import com.realcoders.realcoderlinkedinspring3.user.User;

public class UserContext {

    private static  ThreadLocal<User> usernameThreads = new ThreadLocal<>();

    public static User getUser() {
        return usernameThreads.get();
    }

    public static void setUser(User user) {
        usernameThreads.set(user);
    }
}
