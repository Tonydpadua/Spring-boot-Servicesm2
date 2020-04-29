package com.tonydpadua.security;

import org.apache.catalina.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserService {


    public static UserSS authenticated(){
        try {
            return (UserSS) SecurityContextHolder.getContext()
                    .getAuthentication().getPrincipal();
        }
        catch (Exception e){
            return null;
        }
    }



}
