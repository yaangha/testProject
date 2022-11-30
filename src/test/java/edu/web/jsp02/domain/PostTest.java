package edu.web.jsp02.domain;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PostTest {
    // Log4j 사용하기 위해서
    private static final Logger log = LoggerFactory.getLogger(PostTest.class);
    
    @Test
    public  void testPostBuilder() {
        // Builder 패턴을 이용한 객체 생성 테스트
        // Post p = new Post(null, null, null, null, null, null);
        Post post = Post.builder()
                .id(10)
                .title("Builder 테스트")
                .content("Builder 패턴을 이용한 Post 객체 생성")
                .author("admin")
                .createdTime(LocalDateTime.now())
                .modifiedTime(LocalDateTime.now())
                .build();
        Assertions.assertNotNull(post); 
        log.info(post.toString());
        
        Assertions.assertEquals(10, post.getId());
        Assertions.assertEquals("Builder 테스트", post.getTitle());    
        
    }
    

}
