package edu.web.jsp02.service;

import java.util.List;

import edu.web.jsp02.domain.Post;
import edu.web.jsp02.dto.PostCreateDto;
import edu.web.jsp02.dto.PostUpdateDto;
import edu.web.jsp02.repository.PostDao;
import edu.web.jsp02.repository.PostDaoImpl;
import lombok.extern.slf4j.Slf4j;

// Request --> Controller --> Service --> Repository
// Service(Business) 계층을 담당하는 클래스

@Slf4j // Slf4j의 Logger 객체가 생성됨.
public class PostServiceImpl implements PostService {
    
    private PostDao postDao; // DB select, insert, update, delete 기능
    
    // Singleton
    private static PostServiceImpl instance = null;
    
    private PostServiceImpl() {
        postDao = PostDaoImpl.getInstance();
    }
    
    public static PostServiceImpl getInstance() {
        if (instance == null) {
            instance = new PostServiceImpl();
        }
        return instance;
    }

    @Override
    public List<Post> read() {
        log.info("read()");
        
        return postDao.select();
    }

    @Override
    public int create(PostCreateDto dto) {
        log.info("create(dto={})", dto);
        return postDao.insert(dto.toEntity());
    }

    @Override
    public Post read(Integer id) {
        log.info("read(id = {})", id);
        
        // PostDao 객체의 메서드를 호출해서 DB 테이블에서 id로 검색.
        return postDao.selectById(id);
    }

    @Override
    public int delete(Integer id) {
        log.info("delete(id = {})", id);
       
        // 데이터베이스에서 id로 레코드를 삭제.
        return postDao.delete(id);
    }

    @Override
    public int update(PostUpdateDto dto) {
        log.info("update(dto = {})", dto);
        
        // DTO를 Entity로 변환해서 Repository 계층의 메서드를 호출.        
        // update된 행의 개수를 리턴.
        return postDao.update(dto.toEntity());
    }
    
    @Override
    public List<Post> search(String type, String keyword) {
        log.info("search(type = {}, keyword = {})", type, keyword);
        
        return postDao.selectByKeyword(type, keyword);
    }

}
