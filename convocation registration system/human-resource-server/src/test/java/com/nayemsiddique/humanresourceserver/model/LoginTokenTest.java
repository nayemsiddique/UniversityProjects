package com.nayemsiddique.humanresourceserver.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginTokenTest {
    @Test
    public void insert() {
        LoginToken loginToken = new LoginToken("a", new Name("Monirul", "Hasan"), Role.coordinator);
    }
}
