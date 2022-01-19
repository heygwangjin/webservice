package com.webservice.springboot.config.auth.dto;

import com.webservice.springboot.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    // SessionUser에는 인증된 사용자 정보만 필요하기 때문에 아래의 필드들만 선언
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
