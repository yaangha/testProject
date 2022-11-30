package edu.web.jsp02.domain;

import java.time.LocalDateTime;

// MVC 아키텍쳐에서 Model에 해당하는 클래스. DB의 POSTS 테이블의 내용.
// Model 클래스: (1) 필드, (2) 생성자, (3) getters, (4) toString
public class Post {
    private Integer id; // 글 번호(Primary Key)
    private String title;// 글 제목
    private String content; // 글 내용
    private String author; // 작성자
    private LocalDateTime createdTime; // 최초 작성 시간
    private LocalDateTime modifiedTime; // 최종 수정 시간.
    
    public Post() {}

    public Post(Integer id, String title, String content, String author, LocalDateTime createdTime,
            LocalDateTime modifiedTime) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.createdTime = createdTime;
        this.modifiedTime = modifiedTime;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public LocalDateTime getModifiedTime() {
        return modifiedTime;
    }

    @Override
    public String toString() {
        return "Post [id=" + id + ", title=" + title + ", content=" + content + ", author=" + author + ", createdTime="
                + createdTime + ", modifiedTime=" + modifiedTime + "]";
    }
    
    // Builder(Factory) 패턴
    public static PostBuilder builder() {
        return new PostBuilder();
    }
    
    public static class PostBuilder { // 내부 클래스(외부 클래스와 동일하게 멤버 만들기)
        private Integer id;
        private String title;
        private String content;
        private String author;
        private LocalDateTime createdTime;
        private LocalDateTime modifiedTime;
        
        public PostBuilder id(Integer id) {
            this.id = id;
            return this;
        }
        
        public PostBuilder title(String title) {
            this.title = title;
            return this;
        }
        
        public PostBuilder content(String content) {
            this.content = content;
            return this;
        }
        
        public PostBuilder author(String author) {
            this.author = author;
            return this;
        }
        
        public PostBuilder createdTime(LocalDateTime createdTime) {
            this.createdTime = createdTime;
            return this;
        }
        
        public PostBuilder modifiedTime(LocalDateTime modifiedTime) {
            this.modifiedTime = modifiedTime;
            return this;
        }
        
        public Post build() {
            return new Post(id, title, content, author, createdTime, modifiedTime);
        }
        
    }
        
}
