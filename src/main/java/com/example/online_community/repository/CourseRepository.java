package com.example.online_community.repository;

import com.example.online_community.model.Course;

import java.util.List;
import java.util.Optional;

public interface CourseRepository {
    Course save(Course course); // 저장
    Optional<Course> findById(Long id); // ID 조회
    List<Course> findAll();
    void deleteBYId(Long id); // ID 기준 삭제

    List<Course> findByTitleContaining(String keyword); // 제목 확인
    List<Course> findByCategory(String category); // 카테고리 기준
    List<Course> findByTitleContainingAndCategory(String keyword, String category); // 카테고리 & 제목
}