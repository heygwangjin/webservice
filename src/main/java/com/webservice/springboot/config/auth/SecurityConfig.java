package com.webservice.springboot.config.auth;

import com.webservice.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity // Spring Security 설정들 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;

    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable() // h2-console 화면 사용을 위해 옵션들 disable
                .and()
                .authorizeRequests() // URL별 권한 관리를 설정하는 옵션의 시작점
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll() // 해당 URL들은 전체 열람 권한을 준다.
                .antMatchers("/api/v1/**").hasRole(Role.USER.name()) // 해당 API는 USER 권한을 가진 사람만 가능하다.
                .anyRequest().authenticated() // 위에 명시한 URL 외의 나머지는 모두 인증된 사용자들에게만 허용 (로그인 사용자)
                .and()
                .logout()
                .logoutSuccessUrl("/") // logout 성공시 리다이렉트할 URL을 설정하는 메소드
                .and()
                .oauth2Login() // OAuth2 로그인 기능에 대한 여러 설정의 진입점
                .userInfoEndpoint() // OAuth2 로그인 성공 이후 사용자 정보를 가져올 때의 설정들을 담당
                .userService(customOAuth2UserService); // 소셜 로그인 성공 시 후속 조치를 진행할 UserService 인터페이스의 구현체를 등록
                // 리소스 서버(소셜 서비스)에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능을 명시할 수 있다.
    }
}
