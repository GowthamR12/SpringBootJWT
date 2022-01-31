package com.Palnar.HRApplication.controller;




import java.io.IOException;
import java.util.List;


import com.Palnar.HRApplication.model.JwtRequest;
import com.Palnar.HRApplication.model.JwtResponse;

import com.Palnar.HRApplication.service.UserService;
import com.Palnar.HRApplication.utility.JWTUtility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

@RestController

@CrossOrigin(origins="*")
public class HomeController {


    @Autowired
    private JWTUtility jwtUtility;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;

   
   
    @GetMapping("/hm")
    public String home(){
        return "Welcome home";
    }

    @PostMapping("/authenticate")
    
    public JwtResponse<String,HttpStatus> authenticate(@RequestBody JwtRequest jwtRequest) throws Exception{

        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                jwtRequest.getUsername(),
                jwtRequest.getPassword()
            ));
        }

        catch(BadCredentialsException e){
            throw new Exception("Invalid_Credentials",e);
        }
        

        final UserDetails userDetails = userService.loadUserByUsername(jwtRequest.getUsername());

        final String token = jwtUtility.generateToken(userDetails);
       

        return new JwtResponse<String,HttpStatus>(token,HttpStatus.OK);
    }


    
}
