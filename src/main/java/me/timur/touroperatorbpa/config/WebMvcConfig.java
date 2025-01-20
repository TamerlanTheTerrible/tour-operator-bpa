//package me.timur.touroperatorbpa.config;
//
//import lombok.RequiredArgsConstructor;
//import me.timur.touroperatorbpa.annotation.impl.AuthorizedUserResolver;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.method.support.HandlerMethodArgumentResolver;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import java.util.List;
//
///**
// * Created by Temurbek Ismoilov on 06/02/22.
// */
//
//@Configuration
//@EnableWebMvc
//@RequiredArgsConstructor
//public class WebMvcConfig implements WebMvcConfigurer {
//
//    private final AuthorizedUserResolver authorizationUserResolver;
//
//    @Override
//    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
//        resolvers.add(authorizationUserResolver);
//    }
//}
