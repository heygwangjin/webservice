package com.webservice.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Posts 클래스는 실제 DB의 테이블과 매칭될 클래스이며, 보통 Entity 클래스라고도 한다.
 *
 * @Entity가 붙은 클래스는 테이블과 링크 된다.
 *
 * @Column 을 굳이 선언하지 않아도 해당 클래스의 필드는 모두 칼럼이 된다.
 * 그러나, 디폴트 값 외에 추가로 변경이 필요한 옵션이 있는 경우, 선언이 필요하다.
 */

@Getter
@NoArgsConstructor
@Entity // 주요 어노테이션을 클래스에 가깝게 두면 롬복이 더 이상 필요 없을 경우에 삭제하기 좋다.
public class Posts {

    @Id // 해당 테이블의 PK 필드를 나타낸다.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK의 생성 규칙을 나타낸다.
    private Long id;

    @Column(length = 500, nullable = false) // VARCHAR(255)가 기본이나, length를 500으로 변경
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false) // VARCHAR -> TEXT
    private String content;

    private String author;

    /**
     * @Builder
     * 해당 클래스의 빌더 패턴 클래스를 생성
     * 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함 (title, content, author)
     * 생성자처럼 생성 시점에 값을 채워주는 역할은 동일하나, 생성자와 다르게 지금 채워야할 필드가 무엇인지 명확히 지정이 가능하다.
     */
    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
