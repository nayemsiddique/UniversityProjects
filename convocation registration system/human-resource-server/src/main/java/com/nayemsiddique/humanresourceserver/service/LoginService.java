package com.nayemsiddique.humanresourceserver.service;

import com.nayemsiddique.humanresourceserver.exception.FileAlreadyExistsExceptions;
import com.nayemsiddique.humanresourceserver.exception.FileDoesnotExistsException;
import com.nayemsiddique.humanresourceserver.model.Login;
import com.nayemsiddique.humanresourceserver.repository.LoginRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {
    private LoginRepository loginRepository;

    public LoginService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    public Boolean insert(Login login) {
        Optional<Login> byId = loginRepository.findById(login.getId());
        if (byId.isPresent()) {
            try {
                throw new FileAlreadyExistsExceptions(login.getId());
            } catch (FileAlreadyExistsExceptions fileAlreadyExistsExceptions) {
                fileAlreadyExistsExceptions.printStackTrace();
                return false;
            }
        }
        loginRepository.save(login);
        return true;
    }

    public Boolean update(String id, Login login) {
        Optional<Login> byId = loginRepository.findById(id);
        if (byId.isPresent()) {
            login.setId(id);
            loginRepository.save(login);
            return true;
        }
        try {
            throw new FileDoesnotExistsException(id);
        } catch (FileDoesnotExistsException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean findById(String id) {
        Optional<Login> byId = loginRepository.findById(id);
        return byId.isPresent();

    }
}
