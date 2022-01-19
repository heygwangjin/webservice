package com.webservice.springboot.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * * 메소드 인자로 세션값을 바로 받을 수 있게 어노테이션 생성
 */

@Target(ElementType.PARAMETER) // 이 어노테이션이 생성될 수 있는 위치는 메소드의 파라미터로 선언된 객체에서만 사용이 가능하다.
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser {
}
