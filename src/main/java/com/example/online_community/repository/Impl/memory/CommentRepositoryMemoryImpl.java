package com.example.online_community.repository.Impl.memory;

import com.example.online_community.model.Comment;
import com.example.online_community.repository.CommentRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@Profile("memory")
public class CommentRepositoryMemoryImpl implements CommentRepository {

    private final Map<Long, Comment> store = new HashMap<>();
    private Long nextId = 1L;

    /* 저장 */
    @Override
    public Comment save(Comment comment) {
        System.out.println("[MEMORY-REPO] save() 호출: " + comment);
        if(comment.getId() == null){
            comment.setId(nextId++);
        }
        store.put(comment.getId(), comment);
        return comment;
    }

    /* ID 기준 찾기 */
    @Override
    public Optional<Comment> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    /* CourseId 기준찾기 */
    @Override
    public List<Comment> findBYCourseId(Long courseId) {
        List<Comment> list = new ArrayList<>();

        for(Comment c : store.values()){
            if(c.getCourse().getId().equals(courseId)){
                list.add(c);
            }
        }
        return list;
    }

    /* 삭제 */
    @Override
    public void deleteId(Long id) {
        store.remove(id);
    }
}