package com.example.online_community.repository.Impl.jpa;

import com.example.online_community.model.Course;
import com.example.online_community.model.Rating;
import com.example.online_community.repository.CourseRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

/* jap 의존*/
interface SpringDataCourseRepository extends JpaRepository<Course,Long> {
    List<Course> findByTitleContaining(String keyword); // 제목 확인
    List<Course> findByCategory(String category); // 카테고리 기준
    List<Course> findByTitleContainingAndCategory(String keyword, String category); // 카테고리 & 제목
}

@Repository
@Profile("jpa")
public class CourseRepositoryJpaImpl implements CourseRepository {

    private final SpringDataCourseRepository jpaRepo;
    public CourseRepositoryJpaImpl(SpringDataCourseRepository jpaRepo) {
        this.jpaRepo = jpaRepo;
    }

    /* 저장 */
    @Override
    public Course save(Course course) {
        return jpaRepo.save(course);
    }

    /* ID 조회 */
    @Override
    public Optional<Course> findById(Long id) {
        return jpaRepo.findById(id);
    }

    /* 전체 반환 */
    @Override
    public List<Course> findAll() {
        return jpaRepo.findAll();
    }

    /* 삭제 */
    @Override
    public void deleteBYId(Long id) {
        jpaRepo.deleteById(id);
    }

    /* keyword 기준 */
    @Override
    public List<Course> findByTitleContaining(String keyword) {
        /* 비어 있을 시 */
        if(keyword == null || keyword.isBlank()){
            return jpaRepo.findAll();
        }

        return jpaRepo.findByTitleContaining(keyword);
    }

    /* 카테고리 기준 */
    @Override
    public List<Course> findByCategory(String category) {

        if(category == null || category.isBlank()){
            return jpaRepo.findAll();
        }

        return jpaRepo.findByCategory(category);
    }

    /* 카테고리 & 키워드 기준 */
    @Override
    public List<Course> findByTitleContainingAndCategory(String keyword, String category) {

        // keyword 또는 category가 비어 있으면 적절히 분기
        if ((keyword == null || keyword.isBlank())
                && (category == null || category.isBlank())) {
            return jpaRepo.findAll();
        }
        if (keyword == null || keyword.isBlank()) {
            return jpaRepo.findByCategory(category);
        }
        if (category == null || category.isBlank()) {
            return jpaRepo.findByTitleContaining(keyword);
        }
        return jpaRepo.findByTitleContainingAndCategory(keyword,category);
    }
}