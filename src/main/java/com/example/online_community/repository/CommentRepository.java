package com.example.online_community.repository;

import com.example.online_community.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {
    Comment save(Comment comment); /* 저장 */
    Optional<Comment> findById(Long id); /* ID 기준 찾기 */
    List<Comment> findBYCourseId(Long courseId); /* CourseId 기준찾기 */
    void deleteId(Long id); /* 삭제 */
}