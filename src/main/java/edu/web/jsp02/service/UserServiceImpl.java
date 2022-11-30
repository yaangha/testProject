package edu.web.jsp02.service;

import java.util.List;

import edu.web.jsp02.domain.User;
import edu.web.jsp02.dto.UserSignUpDto;
import edu.web.jsp02.dto.UserUpdateDto;
import edu.web.jsp02.repository.UserDao;
import edu.web.jsp02.repository.UserDaoImpl;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserServiceImpl implements UserService {

    private UserDao userDao;
    
    // Singleton
    private static UserServiceImpl instance = null;
    
    private UserServiceImpl() {
        userDao = UserDaoImpl.getInstance();
    }
    
    public static UserServiceImpl getInstance() {
        if (instance == null) {
            instance = new UserServiceImpl();
        }
        return instance;
    }
    
    @Override
    public List<User> read() {
        log.info("read()");
        return userDao.select();
    }
    
    @Override
    public User read(Integer id) {
        log.info("read(id={})", id);
        return userDao.selectById(id);
    }
    
    @Override
    public int signUp(UserSignUpDto dto) {
        log.info("signUp(dto={})", dto);
        return userDao.insert(dto.toEntity());
    }
    
    @Override
    public int update(UserUpdateDto dto) {
        log.info("update(dto={})", dto);
        return userDao.update(dto.toEntity());
    }
    
    @Override
    public int delete(Integer id) {
        log.info("delete(id={})", id);
        return userDao.delete(id);
    }

    @Override
    public List<User> search(String type, String keyword) {
        log.info("search(type={}, keyword={})", type, keyword);
        return userDao.search(type, keyword);
    }

    @Override
    public User signIn(String userName, String password) {
        log.info("signIn(userName={}, password={})", userName, password);
        
        User user = User.builder()
                .userName(userName).password(password)
                .build();
        
        return userDao.selectByUserNameAndPassword(user);
    }
}
