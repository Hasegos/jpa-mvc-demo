package com.example.online_community.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "course_ratings")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
    private int score;
    private LocalDateTime createdAt = LocalDateTime.now();

    public Rating(){}

    public Rating(Long id, User user, Course course, int score) {
        this.id = id;
        this.user = user;
        this.course = course;
        this.score = score;
    }

    public void setId(Long id) { this.id = id; }
    public Long getId() { return id; }

    public void setUser(User user) { this.user = user; }
    public User getUser() { return user; }

    public void setCourse(Course course) { this.course = course; }
    public Course getCourse() { return course; }

    public void setScore(int score) { this.score = score; }
    public int getScore() { return score; }

    public LocalDateTime getCreatedAt() { return createdAt; }
}