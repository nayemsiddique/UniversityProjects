package com.nayemsiddique.humanresourceserver.service;

import com.nayemsiddique.humanresourceserver.exception.FileDoesnotExistsException;
import com.nayemsiddique.humanresourceserver.model.LoginToken;
import com.nayemsiddique.humanresourceserver.repository.LoginTokenRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoginTokenService {
    private LoginTokenRepository loginTokenRepository;

    public LoginTokenService(LoginTokenRepository loginTokenRepository) {
        this.loginTokenRepository = loginTokenRepository;
    }

    public LoginToken insert(LoginToken loginToken) {
        Optional<LoginToken> byId = loginTokenRepository.findById(loginToken.getId());
        if (byId.isPresent()) {
            return null;
        }
        return loginTokenRepository.save(loginToken);
    }

    public List<LoginToken> findAll() {
        return loginTokenRepository.findAll();
    }

    public LoginToken findById(String id) {
        return loginTokenRepository.findById(id).get();
    }

    public LoginToken update(String id, LoginToken loginToken) {
        Optional<LoginToken> byId = loginTokenRepository.findById(id);
        if (byId.isPresent()) {
            loginToken.setId(id);
            return loginTokenRepository.save(loginToken);
        }

        try {
            throw new FileDoesnotExistsException(id);
        } catch (FileDoesnotExistsException e) {
            e.printStackTrace();
            return null;
        }
    }
}
