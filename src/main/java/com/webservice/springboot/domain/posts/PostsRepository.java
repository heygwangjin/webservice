package com.webservice.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Entity 클래스와 기본 Entity Repository는 함께 위치해야 한다.
 * 프로젝트가 커져 도메인별로 분리해야 한다면, 도메인 패키지에서 위의 둘을 함께 관리해야 한다.
 */
public interface PostsRepository extends JpaRepository<Posts, Long> {

    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
} // 기본적인 CRUD 메소드가 자동으로 생성된다.
