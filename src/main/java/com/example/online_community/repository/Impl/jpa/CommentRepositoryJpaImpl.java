package com.example.online_community.repository.Impl.jpa;

import com.example.online_community.model.Comment;
import com.example.online_community.model.User;
import com.example.online_community.repository.CommentRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

/* jap 의존*/
interface SpringDataCommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByCourseId(Long courseId); /* CourseId 기준찾기 */
}

@Repository
@Profile("jpa")
public class CommentRepositoryJpaImpl implements CommentRepository {

    private final SpringDataCommentRepository jpaRepo;
    public CommentRepositoryJpaImpl(SpringDataCommentRepository jpaRepo) {
        this.jpaRepo = jpaRepo;
    }
    /* 저장 */
    @Override
    public Comment save(Comment comment) {
        return jpaRepo.save(comment);
    }

    /* ID 기준 찾기 */
    @Override
    public Optional<Comment> findById(Long id) {
        return jpaRepo.findById(id);
    }

    /* CourseId 기준찾기 */
    @Override
    public List<Comment> findBYCourseId(Long courseId) {
      return jpaRepo.findByCourseId(courseId);
    }

    /* 삭제 */
    @Override
    public void deleteId(Long id) {
        jpaRepo.deleteById(id);
    }
}