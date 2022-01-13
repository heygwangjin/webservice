package com.webservice.springboot.service.posts;

import com.webservice.springboot.domain.posts.Posts;
import com.webservice.springboot.domain.posts.PostsRepository;
import com.webservice.springboot.web.dto.PostsListResponseDto;
import com.webservice.springboot.web.dto.PostsResponseDto;
import com.webservice.springboot.web.dto.PostsSaveRequestDto;
import com.webservice.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        // JPA의 영속성 컨텍스트가 유지되어서 entity의 값 변경만으로 테이블에 변경분을 반영할 수 있기 때문에, Update 쿼리가 필요 없다. (Dirty checking)
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    @Transactional
    public void delete(Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        postsRepository.delete(posts);
    }

    public PostsResponseDto findById (Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true) // 트랜잭션 범위 유지하되, 조회 기능만 남겨두어 조회 속도가 개선된다. (등록, 수정, 삭제 기능이 전혀 없는 서비스에서 사용 추천)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }
}
