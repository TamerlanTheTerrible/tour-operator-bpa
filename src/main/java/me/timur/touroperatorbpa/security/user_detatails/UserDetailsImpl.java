//package me.timur.touroperatorbpa.security.user_detatails;
//
//import me.timur.touroperatorbpa.domain.entity.User;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//import java.util.Set;
//import java.util.stream.Collectors;
//
///**
// * Created by Temurbek Ismoilov on 04/08/23.
// */
//
//public record UserDetailsImpl(
//        String username,
//        String password,
//        Set<SimpleGrantedAuthority> grantedAuthorities,
//        boolean isAccountNonExpired,
//        boolean isAccountNonBlocked,
//        boolean isCredentialsNonExpired,
//        boolean isEnabled
//) implements UserDetails {
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return grantedAuthorities;
//    }
//
//    @Override
//    public String getPassword() {
//        return password;
//    }
//
//    @Override
//    public String getUsername() {
//        return username;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return isAccountNonExpired;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return isAccountNonBlocked;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return isCredentialsNonExpired;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return isEnabled;
//    }
//
//    public static UserDetailsImpl fromEntity(User user) {
//        return new UserDetailsImpl(
//                user.getEmail(),
//                user.getPassword(),
//                user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet()),
//                true,
//                true,
//                true,
//                user.getIsActive()
//        );
//    }
//}
