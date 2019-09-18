package com.nayemsiddique.humanresourceserver.controller;

import com.nayemsiddique.humanresourceserver.model.LoginToken;
import com.nayemsiddique.humanresourceserver.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/auth")
public class AuthenticationController {
    private AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping
    @ResponseBody
    public LoginToken authentication(@RequestParam String id, @RequestParam String password) {
        return authenticationService.Authentication(id, password);
    }
}
