package me.timur.touroperatorbpa.model.enums;

import lombok.Getter;

/**
 * Created by Temurbek Ismoilov on 20/01/25.
 */

@Getter
public enum Role {
    TOUR_MANAGER("ROLE_TOUR_MANAGER"),
    ACCOMMODATION_MANAGER("ROLE_ACCOMMODATION_MANAGER"),
    FLIGHT_MANAGER("ROLE_FLIGHT_MANAGER"),
    TRAIN_MANAGER("ROLE_TRAIN_MANAGER"),
    TRANSPORT_MANAGER("ROLE_TRANSPORT_MANAGER"),
    FOOD_MANAGER("ROLE_FOOD_MANAGER"),
    BOOKING_MANAGER("ROLE_BOOKING_MANAGER"),
    ADMIN("ROLE_ADMIN"),
    GENERAL_MANAGER("GENERAL_MANAGER"),

    ;

    private final String roleName;

    Role(String roleName) {
        this.roleName = roleName;
    }

}
