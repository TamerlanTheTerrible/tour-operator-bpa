package me.timur.touroperatorbpa.security;

import me.timur.touroperatorbpa.domain.entity.User;

/**
 * Created by Temurbek Ismoilov on 12/03/25.
 */

public class UserContext {
    private static final ThreadLocal<User> userHolder = new ThreadLocal<>();

    public static void setUser(User user) {
        userHolder.set(user);
    }

    public static User getUser() {
        return userHolder.get();
    }

    public static void clear() {
        userHolder.remove();
    }
}
