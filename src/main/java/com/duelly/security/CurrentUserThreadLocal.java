package com.duelly.security;

import com.duelly.entities.User;

public class CurrentUserThreadLocal {
    private static final ThreadLocal<User> currentUser = new ThreadLocal<>();

    private CurrentUserThreadLocal() {

    }

    public static void setCurrentUser(User user) {
        System.out.println("signing with the role "+user.getRole());
        currentUser.set(user);
    }

    public static User getCurrentUser() {
        return currentUser.get();
    }

    public static void clear() {
        currentUser.remove();
    }
}