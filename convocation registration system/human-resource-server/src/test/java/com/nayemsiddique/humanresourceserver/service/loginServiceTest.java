package com.nayemsiddique.humanresourceserver.service;

import com.nayemsiddique.humanresourceserver.model.Login;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class loginServiceTest {
    @Autowired
    private LoginService loginService;

    @Test
    public void insert() {
        Login login = new Login("2016000000159", "12345");
        loginService.insert(login);

    }

}
