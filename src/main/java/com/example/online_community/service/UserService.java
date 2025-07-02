package com.example.online_community.service;

import com.example.online_community.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User register(User user); // 회원가입
    User save(User user); // 회원 정보 수정
    Optional<User> findById(Long id); // 단건 조회
    List<User> findAll(); // 전체 조회
    void delete (Long id); // 정보 삭제

    Optional<User> findByUsername(String username); // 이메일 기준 찾기
    User authenticate(String username, String password); // 회원 검사
}