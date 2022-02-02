package com.intuit.user.controller;

import com.intuit.user.model.JwtRequest;
import com.intuit.user.model.JwtResponse;
import com.intuit.user.model.UserData;
import com.intuit.user.service.UserService;
import com.intuit.user.utility.JwtUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RequestMapping("")
@RestController
public class UserController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtility jwtUtility;

    @Autowired
    private UserData user;


    @GetMapping("/test")
    public String getRequestToken(){
        return "test";
    }

    @PostMapping("save")
    public String saveDetails(@RequestBody UserData user){

        userService.saveDetails(user);
        return "User created";
    }

    @GetMapping("exchange")
    public UserData exchangeToken(@RequestHeader("Authorization") String token){
        System.out.println("exchange token :::::::::"+token);
        return userService.exchangeToken(token);
    }


    @PostMapping("authenticate")
    public JwtResponse authenticate(@RequestBody JwtRequest jwtRequest) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequest.getUserName(), jwtRequest.getPassword())

            );
        }catch (BadCredentialsException e){
            throw new Exception("INVAILD CRED", e);
        }

        final UserDetails userDetails=userService.loadUserByUsername(jwtRequest.getUserName());

        final String token= jwtUtility.generateToken(userDetails);


        return new JwtResponse(token);
    }


}


