package com.example.online_community.service;

import com.example.online_community.model.Course;
import com.example.online_community.model.User;

import java.util.List;
import java.util.Optional;

public interface CourseService {

    Course saveCourse(Course course); // 새 강의 등록
    Optional<Course> getCourse(Long id); // 단건 조회
    List<Course> getAllCourse();  // 전체 목록
    List<Course> searchCourse(String keyword, String category); // 키워드 & 카테고리 기준
    void deleteCourse(Long id); // 삭제

    void toggleLike(Long courseId, User user); // 좋아요 버튼
}