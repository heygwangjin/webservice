package com.webservice.springboot.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 사용자의 권한을 관리한다.
 */

@Getter
@RequiredArgsConstructor
public enum Role {

    // 스프링 시큐리티에서는 권한 코드에 항상 ROLE_이 앞에 있어야만 한다.
    GUEST("ROLE_GUEST", "손님"),
    USER("ROLE_USER", "일반 사용자");

    private final String key;
    private final String title;
}
