//package me.timur.touroperatorbpa.security.controller;
//
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import me.timur.touroperatorbpa.model.response.BaseResponse;
//import me.timur.touroperatorbpa.security.model.SignInDto;
//import me.timur.touroperatorbpa.security.model.TokenDTO;
//import me.timur.touroperatorbpa.security.service.AuthenticationService;
//import org.springframework.web.bind.annotation.*;
//
///**
// * Created by Temurbek Ismoilov on 06/08/23.
// */
//
//@RestController
//@RequestMapping("/sign-in")
//@RequiredArgsConstructor
//public class AuthenticationController {
//
//    private final AuthenticationService authenticationService;
//
//    @PostMapping(value = {"", "/"})
//    public BaseResponse<TokenDTO> signIn(@Valid @RequestBody SignInDto signInDto) {
//        var token = authenticationService.login(signInDto.getEmail(), signInDto.getPassword());
//        return BaseResponse.ok(token);
//    }
//}
