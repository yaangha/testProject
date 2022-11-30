package edu.web.jsp02.repository;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import edu.web.jsp02.domain.User;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserDaoTest {
    
    private UserDao userDao = UserDaoImpl.getInstance();
    
    @Test
    public void test() {
        List<User> list = userDao.select();
        for (User u : list) {
            log.info(u.toString());
        }
        
        User entity = User.builder()
                .userName("test").password("1234").email("test@gmail.com").build();
        
        int result = userDao.insert(entity);
        Assertions.assertEquals(1, result);
        
    }
            
}
