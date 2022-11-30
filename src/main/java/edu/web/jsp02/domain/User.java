package edu.web.jsp02.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString
public class User {
    private Integer id; // 회원번호
    private String userName; // 회원 아이디
    private String password; // 비밀번호
    private String email; // 이메일
    private Integer points; // 포인트

}
