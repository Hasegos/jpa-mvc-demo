package com.example.online_community.service.Impl;

import com.example.online_community.model.Comment;
import com.example.online_community.model.Course;
import com.example.online_community.model.User;
import com.example.online_community.repository.CommentRepository;
import com.example.online_community.repository.CourseRepository;
import com.example.online_community.service.CommentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CourseRepository courseRepository;

    public CommentServiceImpl(CommentRepository commentRepository, CourseRepository courseRepository) {
        this.commentRepository = commentRepository;
        this.courseRepository = courseRepository;
    }

    /* 댓글 추가 */
    @Override
    public Comment addComment(Long courseId, User user, String content) {
        Course c = courseRepository.findById(courseId)
                .orElseThrow(() -> new NoSuchElementException("해당 요소는 존재하지 않습니다."));

        Comment comment = new Comment();
        comment.setCourse(c);
        comment.setUser(user);
        comment.setContent(content);

        Comment saved = commentRepository.save(comment);
        c.getComments().add(saved);
        courseRepository.save(c);

        return saved;
    }
}