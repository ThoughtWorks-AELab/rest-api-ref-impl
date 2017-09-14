package com.thoughtworks.dps.restapirefimpl.controllers;

import com.thoughtworks.dps.restapirefimpl.entities.Message;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Optional;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

// This just exists to show off 401 vs 403 honestly
@RestController
@RequestMapping("/hello")
public class HelloController {

    @RequestMapping(method = GET)
    public ResponseEntity<Message> get(@RequestParam Optional<String> name, Principal principal) {
        String toGreet = name.orElse(principal.getName());
        return new ResponseEntity<>(new Message(String.format("Hello, %s!", toGreet)), OK);
    }

    @RequestMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Message> realGet() {
        return new ResponseEntity<>(new Message("Hi admin!"),OK);
    }
}
