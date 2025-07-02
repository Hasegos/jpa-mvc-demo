package com.example.online_community.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    private String content;
    private LocalDateTime createdAt = LocalDateTime.now();

    public Comment(){}

    public Comment(Long id, User user, Course course, String content) {
        this.id = id;
        this.user = user;
        this.course = course;
        this.content = content;
    }

    public void setId(Long id) { this.id = id; }
    public Long getId() { return id; }

    public void setUser(User user) { this.user = user; }
    public User getUser() { return user; }

    public void setCourse(Course course) { this.course = course; }
    public Course getCourse() { return course; }

    public void setContent(String content) { this.content = content; }
    public String getContent() { return content; }

    public LocalDateTime getCreatedAt() { return createdAt; }
}