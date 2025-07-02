package com.example.online_community.repository.Impl.jpa;

import com.example.online_community.model.Rating;
import com.example.online_community.model.User;
import com.example.online_community.repository.RatingRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

/* jap 의존*/
interface SpringDataRatingRepository extends JpaRepository<Rating,Long> {
    List<Rating> findByCourseId(Long courseId); /* CourseId 기준찾기 */
}

@Repository
@Profile("jpa")
public class RatingRepositoryJpaImpl implements RatingRepository {

    private final SpringDataRatingRepository jpaRepo;

    public RatingRepositoryJpaImpl(SpringDataRatingRepository jpaRepo) {
        this.jpaRepo = jpaRepo;
    }

    /* 저장 */
    @Override
    public Rating save(Rating rating) {
        return jpaRepo.save(rating);
    }

    /* ID 기준 찾기 */
    @Override
    public Optional<Rating> findById(Long id) {
        return jpaRepo.findById(id);
    }

    /* CourseId 기준찾기 */
    @Override
    public List<Rating> findByCourseId(Long courseId) {
      return jpaRepo.findByCourseId(courseId);
    }

    /* 삭제 */
    @Override
    public void deleteId(Long id) {
        jpaRepo.deleteById(id);
    }
}