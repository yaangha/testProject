package edu.web.jsp02.repository;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import edu.web.jsp02.domain.Post;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SearchTest {

    private PostDao postDao = PostDaoImpl.getInstance();
    
    @Test
    public void test() {
        List<Post> list = postDao.selectByKeyword("t", "te");
        for (Post p : list) {
            Assertions.assertTrue(p.getTitle().toLowerCase().contains("te"));
            //-> argument가 true이면 단위 테스트 성공, false이면 단위 테스트 실패.
            log.info(p.toString());
        }
        
        list = postDao.selectByKeyword("c", "TEST");
        for (Post p : list) {
            Assertions.assertTrue(p.getContent().toLowerCase().contains("test"));
            log.info(p.toString());
        }
        
        list = postDao.selectByKeyword("tc", "test");
        for (Post p : list) {
            Assertions.assertTrue(
                    p.getTitle().toLowerCase().contains("test") || 
                    p.getContent().toLowerCase().contains("test")
            );
            log.info(p.toString()); // p라고 쓰면 안됨(info는 argument로 String 타입만 가능하기 때문에..)
        }
        
        list = postDao.selectByKeyword("a", "Adm");
        for (Post p : list) {
            Assertions.assertTrue(p.getAuthor().toLowerCase().contains("adm"));
            log.info(p.toString());
        }
        
    }

}
