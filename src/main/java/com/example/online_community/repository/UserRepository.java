package com.example.online_community.repository;

import com.example.online_community.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository{
    User save(User user); // 회원 저장
    Optional<User> findById(Long id); // ID 조회
    List<User> findAll();
    void deleteById(Long id); // ID 기준 삭제
    Optional<User> findByUsername(String username); // 이메일 기준 찾기

    boolean existsByUsername(String username); // 이미 존재하는지 확인
}