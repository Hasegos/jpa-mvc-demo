package com.example.online_community.service;

import com.example.online_community.model.Comment;
import com.example.online_community.model.User;

public interface CommentService {
    Comment addComment(Long courseId, User user, String content); /* 댓글 추가 */
}