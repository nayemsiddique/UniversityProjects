package com.nayemsiddique.humanresourceserver.controller;

import com.nayemsiddique.humanresourceserver.model.Login;
import com.nayemsiddique.humanresourceserver.service.LoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/login")
public class LoginController {
    private LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("")
    public ResponseEntity<Boolean> insert(@RequestBody Login login) {
        Boolean insert = loginService.insert(login);
        if (insert) return ResponseEntity.ok(true);
        return ResponseEntity.badRequest().body(false);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> update(@PathVariable String id, @RequestBody Login login) {
        Boolean update = loginService.update(id, login);
        if (update) return ResponseEntity.ok(true);
        return ResponseEntity.badRequest().body(false);
    }

    @GetMapping("?id={id}")
    public ResponseEntity<Boolean> findById(@PathVariable String id) {
        Boolean byId = loginService.findById(id);
        if (byId) return ResponseEntity.ok(true);
        return ResponseEntity.noContent().build();
    }
}
