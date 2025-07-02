package com.example.online_community.repository;

import com.example.online_community.model.Rating;

import java.util.List;
import java.util.Optional;

public interface RatingRepository{
    Rating save(Rating rating); /* 저장 */
    Optional<Rating> findById(Long id); /* ID 기준 찾기 */
    List<Rating> findByCourseId(Long courseId); /* CourseId 기준찾기 */
    void deleteId(Long id); /* 삭제 */
}