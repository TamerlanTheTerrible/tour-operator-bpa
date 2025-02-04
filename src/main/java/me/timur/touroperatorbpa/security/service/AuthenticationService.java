package me.timur.touroperatorbpa.security.service;

import me.timur.touroperatorbpa.security.model.TokenDTO;

/**
 * Created by Temurbek Ismoilov on 06/08/23.
 */

public interface AuthenticationService {
    TokenDTO login(String username, String password);
}
