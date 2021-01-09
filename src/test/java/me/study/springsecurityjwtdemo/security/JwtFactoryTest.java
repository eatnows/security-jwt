package me.study.springsecurityjwtdemo.security;

import me.study.springsecurityjwtdemo.domain.Account;
import me.study.springsecurityjwtdemo.domain.UserRole;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class JwtFactoryTest {

    private static final Logger log = LoggerFactory.getLogger(JwtFactoryTest.class);

    private AccountContext context;

    @Autowired
    private JwtFactory factory;

    @Before
    public void setUp() {
        Account account = new Account();
        log.error("userId: {}, password: {}, role: {}", account.getUserId(), account.getPassword(), account.getUserRole().getRoleName());
        this.context = AccountContext.fromAccountModel(account);
    }

    @Test
    public void TEST_JWT_GENERATE() {
        // log 레벨을 설정해야하지만 그냥 error로 설정
        log.error(factory.generateToken(this.context));
    }

}