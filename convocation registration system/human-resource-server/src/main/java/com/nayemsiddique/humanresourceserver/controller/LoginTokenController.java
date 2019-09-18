package com.nayemsiddique.humanresourceserver.controller;

import com.nayemsiddique.humanresourceserver.model.LoginToken;
import com.nayemsiddique.humanresourceserver.service.LoginTokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/tokens")
public class LoginTokenController {
    private LoginTokenService loginTokenService;

    public LoginTokenController(LoginTokenService loginTokenService) {
        this.loginTokenService = loginTokenService;
    }

    @PostMapping("")
    public ResponseEntity<LoginToken> insert(@RequestBody LoginToken loginToken) {
        LoginToken insert = loginTokenService.insert(loginToken);
        if (insert == null) return ResponseEntity.badRequest().body(loginToken);
        return ResponseEntity.ok(insert);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LoginToken> update(@PathVariable String id, @RequestBody LoginToken loginToken) {
        LoginToken update = loginTokenService.update(id, loginToken);
        if (update == null) return ResponseEntity.badRequest().body(loginToken);
        return ResponseEntity.ok(loginToken);
    }

    @GetMapping("")
    public ResponseEntity<List<LoginToken>> findAll() {
        List<LoginToken> all = loginTokenService.findAll();
        if (all.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(all);
    }

    @GetMapping("?id={id}")
    public ResponseEntity<LoginToken> findById(@PathVariable String id) {
        LoginToken byId = loginTokenService.findById(id);
        if (byId == null) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(byId);
    }


}
