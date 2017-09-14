package com.thoughtworks.dps.restapirefimpl.controllers;

import com.thoughtworks.dps.restapirefimpl.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    public UserController() {
    }

    @RequestMapping(path = "/current", method = GET)
    public ResponseEntity<User> getCurrent(Principal principal) {
        User user = (User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        return new ResponseEntity<User>(user, OK);
    }

}
