package com.hu.oleg.blogproject.service;

import com.hu.oleg.blogproject.dto.CommentDto;
import com.hu.oleg.blogproject.entity.Comment;
import com.hu.oleg.blogproject.error.BadRequestException;
import com.hu.oleg.blogproject.error.ResourceNotFound;
import com.hu.oleg.blogproject.mapper.CommentMapper;
import com.hu.oleg.blogproject.repository.CommentRepository;
import com.hu.oleg.blogproject.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    private final CommentMapper commentMapper;
//    private final ModelMapper modelMapper;
//    private final TypeMap<Comment,CommentDto> toDto;
//    private final TypeMap<CommentDto, Comment> toComment;

//    public CommentServiceImpl(
//            CommentRepository commentRepository,
//            PostRepository postRepository,
//            ModelMapper modelMapper) {
//        this.commentRepository = commentRepository;
//        this.postRepository = postRepository;
//        this.modelMapper = modelMapper;
//        toDto = modelMapper.createTypeMap(Comment.class, CommentDto.class);
//        toComment = modelMapper.createTypeMap(CommentDto.class, Comment.class);
//    }

    @Override
    public CommentDto createComment(long postId, CommentDto dto) {
        var post = postRepository.findById(postId).
                orElseThrow(()-> new ResourceNotFound("Post","id",postId));

        var comment = commentMapper.toEntity(dto);
        comment.setPost(post);

        var saved = commentRepository.save(comment);

        return commentMapper.toDto(saved);
    }

    @Override
    public List<CommentDto> findCommentsByPostId(long postId) {
        List<Comment> comments = commentRepository.findCommentsByPostId(postId);

        return comments.stream().map(commentMapper::toDto).toList();
    }

    @Override
    public CommentDto findCommentById(long commentId) {
        var comment = commentRepository.findById(commentId)
                .orElseThrow(()->new ResourceNotFound("Comment","id",commentId ));
        return commentMapper.toDto(comment);
    }

    @Override
    public CommentDto updateCommentById(long id, CommentDto commentDto) {
        //get the original comment from database
        var comment = commentRepository.findById(id)
                .orElseThrow(resourceNotFound(id));

        if (commentDto.getId() == null){
            commentDto.setId(id);
        }else if (commentDto.getId()!=id) throw new BadRequestException("Comment","Id must match");

        //set the post id(required)
        var beforeSave = commentMapper.toEntity(commentDto);
        beforeSave.setPost(comment.getPost());

        return commentMapper.toDto(commentRepository.save(beforeSave));
    }

    @Override
    public CommentDto deleteCommentById(long id) {
        var comment = commentRepository.findById(id).orElseThrow(resourceNotFound(id));
        commentRepository.deleteById(id);
        return commentMapper.toDto(comment);
    }
    private Supplier<RuntimeException> resourceNotFound(long id){
       return  ()-> new ResourceNotFound("Comment","id",id);
    }
}
