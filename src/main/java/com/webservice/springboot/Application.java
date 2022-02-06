package com.webservice.springboot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // 프로젝트의 메인 클래스, 해당 어노테이션이 있는 위치부터 설정을 읽어가기 때문에 프로젝트 최상단에 위치해야 한다.
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args); // 내장 WAS 실행 (서버에 톰캣 설치 필요 x)
    }
}
