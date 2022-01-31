package com.Palnar.HRApplication.model;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.lang.String;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse<String,HttpStatus> {

  
    public JwtResponse(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public JwtResponse(String token, HttpStatus ok) {
        this.jwtToken = token;
        this.jwtResponse = ok;

    }

    private String jwtToken;
    private HttpStatus jwtResponse;

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public HttpStatus getJwtresponse() {
        return jwtResponse;
    }

    public void setResponse(HttpStatus jwtResponse) {
        this.jwtResponse = jwtResponse;
    }

    
    
}
