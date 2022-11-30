package edu.web.jsp02.service;

import java.util.List;

import edu.web.jsp02.domain.User;
import edu.web.jsp02.dto.UserSignUpDto;
import edu.web.jsp02.dto.UserUpdateDto;

public interface UserService {
    
    public List<User> read();
    public User read(Integer id);
    public int signUp(UserSignUpDto dto);
    public int update(UserUpdateDto dto);
    public int delete(Integer id);
    public List<User> search(String type, String keyword);
    public User signIn(String userName, String password);

}
