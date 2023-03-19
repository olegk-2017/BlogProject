package com.hu.oleg.blogproject.service;

import com.hu.oleg.blogproject.dto.PostDto;
import com.hu.oleg.blogproject.dto.PostPageDto;
import com.hu.oleg.blogproject.entity.Post;
import com.hu.oleg.blogproject.error.BadRequestException;
import com.hu.oleg.blogproject.error.ResourceNotFound;
import com.hu.oleg.blogproject.mapper.PostMapper;
import com.hu.oleg.blogproject.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final PostMapper postMapper;
//    private final TypeMap<Post, PostDto> toDto;
//    private final TypeMap<PostDto, Post> toPost;
//
//    private final ModelMapper mapper;
//
//    public PostServiceImpl(PostRepository postRepository, ModelMapper mapper) {
//        this.postRepository = postRepository;
//        this.mapper = mapper;
//        toDto = mapper.createTypeMap(Post.class, PostDto.class);
//        toPost = mapper.createTypeMap(PostDto.class, Post.class);
//    }


    @Override
    public PostDto createPost(PostDto dto) {
        //map the dto to Entity
        var post = postMapper.toEntity(dto);
        //save the entity
        var saved = postRepository.save(post);
        //map the saved entity to DTO

        //return the mapped dto:
        return postMapper.toDto(saved);
    }

    @Override
    public List<PostDto> getAllPost() {
/*
 return postRepository.findAll().stream().map(new Function<Post, PostDto>() {
      @Override
      public PostDto apply(Post post) {
          return postMapper.toDto()(post);
      }
  });
*/

        return postRepository.findAll().stream().map(postMapper::toDto).toList();
    }

    @Override
    public PostDto getPostById(Long id) {
        return  postMapper.toDto(
                postRepository.findById(id)
                        .orElseThrow(()->new ResourceNotFound("Post","id",String.valueOf(id))));

    }

    @Override
    public PostDto updatePostById(Long id, PostDto dto) {
        //assert that a post exists:
        getPostById(id);
        if(dto.getId() == null){
            dto.setId(id);
        }
        if( dto.getId() != id){
            throw new BadRequestException("id", "The id %s supplied must match the id field in the dto".formatted(id));
        }
        var post = postMapper.toEntity(dto);
        var saved = postRepository.save(post);
        return postMapper.toDto(saved);
    }

    @Override
    public void deletePostById(Long id) {
        getPostById(id);
        postRepository.deleteById(id);
    }

    @Override
    public PostPageDto getallPosts(int pageNo  , int pageSize, String sortBy, String sortDir) {
        Pageable pageble = PageRequest.of(pageNo,pageSize, Sort.Direction.fromString(sortDir), sortBy);
        //ctrl+alt+b
        Page<Post> page = postRepository.findAll(pageble);
        return PostPageDto.builder()
                .results(page.getContent().stream().map(postMapper::toDtoWithComments).toList())
                .pageSize(page.getSize())
                .pageNo(page.getNumber())
                .totalElements(page.getTotalElements())
                .isLast(page.isLast())
                .totalPages(page.getTotalPages())
                .build();
    }
}
