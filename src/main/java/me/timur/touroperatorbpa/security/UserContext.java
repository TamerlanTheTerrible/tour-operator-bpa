package me.timur.touroperatorbpa.security;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created by Temurbek Ismoilov on 12/03/25.
 */

public class UserContext {
    private static final ThreadLocal<UserDetails> userHolder = new ThreadLocal<>();

    public static void setUser(UserDetails userDetails) {
        userHolder.set(userDetails);
    }

    public static UserDetails getUser() {
        return userHolder.get();
    }

    public static void clear() {
        userHolder.remove();
    }
}
