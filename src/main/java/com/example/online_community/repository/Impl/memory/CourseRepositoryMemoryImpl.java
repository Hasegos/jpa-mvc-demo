package com.example.online_community.repository.Impl.memory;

import com.example.online_community.model.Course;
import com.example.online_community.repository.CourseRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
@Profile("memory")
public class CourseRepositoryMemoryImpl implements CourseRepository {

    private Map<Long,Course> store = new HashMap<>();
    private Long nextId = 1L;

    /* 저장 */
    @Override
    public Course save(Course course) {
        if(course.getId() == null){
            course.setId(nextId++);
        }
        store.put(course.getId(), course);
        return course;
    }

    /* ID 조회 */
    @Override
    public Optional<Course> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    /* 전체 반환 */
    @Override
    public List<Course> findAll() {
        return new ArrayList<>(store.values());
    }

    /* 삭제 */
    @Override
    public void deleteBYId(Long id) {
        store.remove(id);
    }

    /* keyword 기준 */
    @Override
    public List<Course> findByTitleContaining(String keyword) {

        /* 비어 있을 시 */
        if(keyword == null || keyword.isBlank()){
            return findAll();
        }

        return store.values().stream()
                .filter(u -> u.getTitle().contains(keyword))
                .collect(Collectors.toList());
    }

    /* 카테고리 기준 */
    @Override
    public List<Course> findByCategory(String category) {

        if(category == null || category.isBlank()){
            return findAll();
        }

        return store.values().stream()
                .filter(u -> u.getCategory().contains(category))
                .collect(Collectors.toList());
    }

    /* 카테고리 & 키워드 기준 */
    @Override
    public List<Course> findByTitleContainingAndCategory(String keyword, String category) {
        return store.values().stream()
                .filter(k -> k.getTitle().contains(keyword))
                .filter(c -> c.getCategory().contains(category))
                .collect(Collectors.toList());
    }
}