package com.nayemsiddique.humanresourceserver.service;

import com.nayemsiddique.humanresourceserver.exception.FileDoesnotExistsException;
import com.nayemsiddique.humanresourceserver.model.Login;
import com.nayemsiddique.humanresourceserver.model.LoginToken;
import com.nayemsiddique.humanresourceserver.model.Name;
import com.nayemsiddique.humanresourceserver.model.Role;
import com.nayemsiddique.humanresourceserver.repository.LoginRepository;
import com.nayemsiddique.humanresourceserver.repository.LoginTokenRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {
    private LoginRepository loginRepository;
    private LoginTokenRepository loginTokenRepository;

    public AuthenticationService(LoginRepository loginRepository, LoginTokenRepository loginTokenRepository) {
        this.loginRepository = loginRepository;
        this.loginTokenRepository = loginTokenRepository;
    }

    public LoginToken Authentication(String id, String password) {
        Optional<Login> byId = loginRepository.findById(id);
        if (byId.isPresent()) {
            if (byId.get().getPassword().equals(password)) {
                return loginTokenRepository.findById(byId.get().getId()).get();
            }
        }

        try {
            throw new FileDoesnotExistsException(id);
        } catch (FileDoesnotExistsException e) {
            e.printStackTrace();
            return new LoginToken("", new Name(), Role.noRole);
        }
    }
}
