package com.intuit.user.service;

import com.intuit.user.filter.JwtFilter;
import com.intuit.user.model.UserData;
import com.intuit.user.repository.UserRepository;
import com.intuit.user.utility.JwtUtility;
import org.omg.CORBA.Current;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserData userData;
    @Autowired
    JwtUtility jwtUtility;





    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        String name=userData.getEmailId()+userData.getUserid()+userData.getAccessLevel();
        return new User("admin","password1",new ArrayList<>());
    }
    public String userFetch(){
        UserDetails userDetails=(UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return userDetails.getUsername();
    }

    public UserData exchangeToken(String token){

        UserData user1=new UserData();

        String userid=userFetch();
        System.out.println(userid);
        user1=userRepository.getById(userid);
        return user1;
    }
    public String saveDetails(UserData user1)
    {

        userRepository.save(user1);

        return "Saved";
    }


}
