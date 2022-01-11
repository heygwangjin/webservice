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
 * @Column 을 굳이 선언하지 않아도 해당 클래스의 필드는 모두 DB 테이블의 칼럼이 된다.
 * 그러나, 디폴트 값 외에 추가로 변경이 필요한 옵션이 있는 경우, 선언이 필요하다.
 *
 * Entity 클래스에서는 절대 Setter 메소드를 만들지 않는다. 왜냐하면 해당 클래스의 인스턴스 값들이 언제 어디서 변해야 하는지 코드상으로 명확하게
 * 구분할 수 없기 때문이다.
 */

@Getter
@NoArgsConstructor // @Entity 클래스의 제약사항 (default 생성자 반드시 필요)
@Entity // 주요 어노테이션을 클래스에 가깝게 두자! (위의 어노테이션들은 롬복 어노테이션이므로, 필수는 아니다)
public class Posts {

    @Id // 해당 테이블의 PK 필드를 나타낸다.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK의 생성 규칙을 나타낸다.
    private Long id; // MySQL 기준으로 bigint 타입이 된다.

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
