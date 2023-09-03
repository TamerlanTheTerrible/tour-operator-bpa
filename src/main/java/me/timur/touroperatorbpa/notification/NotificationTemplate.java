package me.timur.touroperatorbpa.notification;

/**
 * Created by Temurbek Ismoilov on 01/09/23.
 */

public enum NotificationTemplate {
    APPLICATION_CREATED("New application."),
    APPLICATION_UPDATED("Application updated."),
    APPLICATION_CANCELLED("Application cancelled."),
    ;

    public final String message;

    NotificationTemplate(String s) {
        this.message = s;
    }
}
