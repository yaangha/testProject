package edu.web.jsp02.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zaxxer.hikari.HikariDataSource;

import edu.web.jsp02.datasource.HikariDataSourceUtil;
import edu.web.jsp02.domain.User;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserDaoImpl implements UserDao {
    
    // singleton
    private static UserDaoImpl instance = null;
    private HikariDataSource ds;
    private UserDaoImpl() {
        ds = HikariDataSourceUtil.getInstance().getDataSource();
    }
    public static UserDaoImpl getInstance() {
        if (instance == null) {
            instance = new UserDaoImpl();
        }
        return instance;
    }
    
    private User recordToEntity(ResultSet rs) throws SQLException {
        Integer id = rs.getInt("ID");
        String userName = rs.getString("USERNAME");
        String password = rs.getString("PASSWORD");
        String email = rs.getString("EMAIL");
        Integer points = rs.getInt("POINTS");
        
        User entity = User.builder()
                .id(id).userName(userName).password(password).email(email).points(points)
                .build();
        
        return entity;
    }
    
    public static final String SQL_SELECT = "select * from USERS order by ID desc";
    
    @Override
    public List<User> select() {
        log.info("select()");
        log.info("SQL: {}", SQL_SELECT);
        
        List<User> list = new ArrayList<>();
        
        try {
            @Cleanup
            Connection conn = ds.getConnection();
            
            @Cleanup
            PreparedStatement stmt = conn.prepareStatement(SQL_SELECT);
            
            @Cleanup
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                User entity = recordToEntity(rs);                
                list.add(entity);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return list;
    }    
    
    public static final String SQL_SELECT_BY_ID =
            "select * from USERS where ID = ?";
    
    @Override
    public User selectById(Integer id) {
        log.info("selectById(id={})", id);
        
        User entity = null;
        
        try {
            @Cleanup
            Connection conn = ds.getConnection();
            
            @Cleanup
            PreparedStatement stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, id);
            
            @Cleanup
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                entity = recordToEntity(rs);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return entity;
    }
    
    public static final String SQL_INSERT =
            "insert into USERS (USERNAME, PASSWORD, EMAIL) values (?, ?, ?)";

    @Override
    public int insert(User entity) {
        log.info("insert(entity={})", entity);
        
        int result = 0; // DB 테이블에 insert한 결과를 저장할 변수
        
        try {
            @Cleanup
            Connection conn = ds.getConnection();
            
            @Cleanup
            PreparedStatement stmt = conn.prepareStatement(SQL_INSERT);
            
            // prepared statement의 바인딩 파라미터(binding parameter) 값들을 세팅.
            stmt.setString(1, entity.getUserName());
            stmt.setString(2, entity.getPassword());
            stmt.setString(3, entity.getEmail());
            
            // SQL 실행            
            result = stmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return result;
    }
    
    public static final String SQL_DELETE = 
            "delete from USERS where ID = ?";

    @Override
    public int delete(Integer id) {
        int result = 0;
        
        try {
            @Cleanup
            Connection conn = ds.getConnection();
            
            @Cleanup
            PreparedStatement stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, id);
            
            result = stmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return result;
    }

    public static final String SQL_UPDATE = 
            "update USERS set USERNAME = ?, PASSWORD = ?, EMAIL = ? where ID = ?";
    
    @Override
    public int update(User entity) {
        int result = 0;
        
        try {
            @Cleanup
            Connection conn = ds.getConnection();
            
            @Cleanup
            PreparedStatement stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, entity.getUserName());
            stmt.setString(2, entity.getPassword());
            stmt.setString(3, entity.getEmail());
            stmt.setInt(4, entity.getId());
            
            result = stmt.executeUpdate();          
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return result;
    }
    
    public static final String SQL_SEARCH_USERNAME = 
            "select * from USERS where lower(USERNAME) like ?";
    public static final String SQL_SEARCH_EMAIL =
            "select * from USERS where lower(EMAIL) like ?";

    @Override
    public List<User> search(String type, String keyword) {
        List<User> list = new ArrayList<>();
        
        try {
            @Cleanup
            Connection conn = ds.getConnection();
            
            @Cleanup
            PreparedStatement stmt = null;
            
            @Cleanup
            ResultSet rs = null;
            
            if (type.equals("u")) {
                stmt = conn.prepareStatement(SQL_SEARCH_USERNAME);
                stmt.setString(1, "%" + keyword.toLowerCase() + "%");
            } else if (type.equals("e")) {
                stmt = conn.prepareStatement(SQL_SEARCH_EMAIL);
                stmt.setString(1, "%" + keyword.toLowerCase() + "%");
            }
            
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                User user = recordToEntity(rs);
                list.add(user);                
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return list;
    }
    
    public static final String SQL_SELECT_BY_USERNAME_AND_PASSWORD = 
            "select * from USERS where USERNAME = ? and PASSWORD = ?";
    
    @Override
    public User selectByUserNameAndPassword(User user) {
        log.info("selectByUserNameAndPassword({})", user);
        
        User entity = null; // DB에서 select한 결과를 저장할 객체
        
        try {
            @Cleanup
            Connection conn = ds.getConnection();
            
            @Cleanup
            PreparedStatement stmt = conn.prepareStatement(SQL_SELECT_BY_USERNAME_AND_PASSWORD);
            log.info(SQL_SELECT_BY_USERNAME_AND_PASSWORD);
            
            stmt.setString(1, user.getUserName());
            stmt.setString(2, user.getPassword());
            
            @Cleanup
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) { // select 검색 결과가 있으면
                entity = recordToEntity(rs);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return entity;
    }

}
