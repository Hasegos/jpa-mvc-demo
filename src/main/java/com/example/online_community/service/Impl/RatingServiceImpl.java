package com.example.online_community.service.Impl;

import com.example.online_community.model.Course;
import com.example.online_community.model.Rating;
import com.example.online_community.model.User;
import com.example.online_community.repository.CourseRepository;
import com.example.online_community.repository.RatingRepository;
import com.example.online_community.service.RatingService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@Transactional
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;
    private final CourseRepository courseRepository;

    public RatingServiceImpl(RatingRepository ratingRepository, CourseRepository courseRepository) {
        this.ratingRepository = ratingRepository;
        this.courseRepository = courseRepository;
    }

    /* rating 추가 */
    @Override
    public Rating addRating(Long courseId, User user, int score) {
        Course c = courseRepository.findById(courseId)
                .orElseThrow(() -> new NoSuchElementException("해당 요소는 존재하지않습니다."));
        Rating r = new Rating();
        r.setCourse(c);
        r.setScore(score);
        r.setUser(user);

        Rating saved = ratingRepository.save(r);
        c.getRatings().add(saved);
        courseRepository.save(c);
        return saved;
    }
}
