//package me.timur.touroperatorbpa.security.user_detatails;
//
//import lombok.RequiredArgsConstructor;
//import me.timur.touroperatorbpa.domain.repository.UserRepository;
//import me.timur.touroperatorbpa.exception.ClientException;
//import me.timur.touroperatorbpa.model.enums.ResponseCode;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.stereotype.Service;
//
///**
// * Created by Temurbek Ismoilov on 06/08/23.
// */
//
//@Service
//@RequiredArgsConstructor
//public class UserDetailsServiceImpl implements UserDetailsService {
//    private final UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) {
//        var user = userRepository.findByEmail(username)
//                .orElseThrow(() -> new ClientException(ResponseCode.RESOURCE_NOT_FOUND, "Could not find user with username: " + username));
//        return UserDetailsImpl.fromEntity(user);
//    }
//}
