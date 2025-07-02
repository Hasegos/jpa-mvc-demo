package com.example.online_community.service;

import com.example.online_community.model.Rating;
import com.example.online_community.model.User;

public interface RatingService {
    Rating addRating(Long courseId, User user, int score); /* 점수 추가 */
}
