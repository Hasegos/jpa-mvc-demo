package com.example.online_community.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String url;
    private String category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private User author;

    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Rating> ratings = new HashSet<>();

    @OneToMany(mappedBy = "course" , cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Comment> comments = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "likes",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> likedUsers = new HashSet<>();

    public Course(Long id, String title, String category, String description, String url,
                  User author, Set<User> likedUsers, Set<Comment> comments, Set<Rating> ratings) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.description = description;
        this.url = url;
        this.author = author;
        this.likedUsers = likedUsers;
        this.comments = comments;
        this.ratings = ratings;
    }

    public Course(){}

    public void setId(Long id) { this.id = id; }
    public Long getId() { return id; }

    public void setTitle(String title) { this.title = title; }
    public String getTitle() { return title; }

    public void setCategory(String category) { this.category = category; }
    public String getCategory() { return category; }

    public void setDescription(String description) { this.description = description; }
    public String getDescription() { return description; }

    public void setUrl(String url) { this.url = url; }
    public String getUrl() { return url; }

    public void setAuthor(User author) { this.author = author; }
    public User getAuthor() { return author; }

    public LocalDateTime getCreatedAt() { return createdAt; }

    public int getLikeCount(){ return likedUsers.size(); }
    public Set<User> getLikedUsers() { return likedUsers; }

    public Set<Comment> getComments() { return comments;}
    public Set<Rating> getRatings() { return ratings; }

    public boolean toggleLike(User u){
        if(likedUsers.contains(u)){
            likedUsers.remove(u);
            return false;
        }else{
            likedUsers.add(u);
            return true;
        }
    }
}