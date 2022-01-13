package com.webservice.springboot.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class) // SpringRunner는 스프링 부트 테스트와 JUnit 사이에 연결자 역할을 한다.
@WebMvcTest(controllers = HelloController.class)
@MockBean(JpaMetamodelMappingContext.class)
public class HelloControllerTest {

    @Autowired
    // 스프링 MVC 테스트의 시작점
    private MockMvc mvc; // 웹 API를 테스트할 때 사용

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello"))
                .andExpect(status().isOk()) // HTTP Header의 Status를 검증 (200, 400, 500 등)
                .andExpect(content().string(hello)); // 응답 본문의 내용 검증
    }

    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "heygwangjin";
        int amount = 26;

        mvc.perform(get("/hello/dto")
                        .param("name", name)
                        .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}
