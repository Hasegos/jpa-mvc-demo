package com.example.online_community.service.Impl;

import com.example.online_community.model.Course;
import com.example.online_community.model.User;
import com.example.online_community.repository.CourseRepository;
import com.example.online_community.service.CourseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    /* 새 강의 등록 */
    @Override
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    /* 단건 조회 */
    @Override
    public Optional<Course> getCourse(Long id) {
        return courseRepository.findById(id);
    }

    /* 전체 목록 조회 */
    @Override
    public List<Course> getAllCourse() {
        return courseRepository.findAll();
    }

    /* 키워드 & 카테고리 기준 찾기 */
    @Override
    public List<Course> searchCourse(String keyword, String category) {
        boolean hasKeyword = keyword != null && !keyword.isBlank();
        boolean hasCategory = category != null && !category.isBlank();

        if(hasCategory && hasKeyword){
            return courseRepository.findByTitleContainingAndCategory(keyword,category);
        }

        if(hasCategory){
            return courseRepository.findByCategory(category);
        }

        if(hasKeyword){
            return courseRepository.findByTitleContaining(keyword);
        }
        return courseRepository.findAll();
    }

    /* 삭제  */
    @Override
    public void deleteCourse(Long id) { courseRepository.deleteBYId(id); }

    @Override
    public void toggleLike(Long courseId, User user) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new NoSuchElementException("해당 요소가 존재하지않습니다."));

        course.toggleLike(user);
        courseRepository.save(course);
    }
}