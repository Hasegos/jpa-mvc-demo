package com.example.online_community.service.Impl;

import com.example.online_community.model.User;
import com.example.online_community.repository.UserRepository;
import com.example.online_community.service.UserService;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /* 회원 가입 */
    @Override
    public User register(User user) {
        if(userRepository.findByUsername(user.getUsername()).isPresent()){
            System.out.println(user.getUsername());
            throw new RuntimeException("이미 존재하는 회원입니다.");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    /* 회원 정보 수정 */
    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    /* 단건 조회 */
    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    /* 전체 조회 */
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    /* 삭제 */
    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    /* 이메일 기준찾기 */
    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /* 이메일, 비밀번호 확인 */
    /*
        @Override
        public User authenticate(String username, String password) {
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new IllegalArgumentException("존재하지않는 아이디입니다."));

            if(!passwordEncoder.matches(password,user.getPassword())){
                throw new IllegalArgumentException("비밀번호가 올바르지 않습니다.");
            }
            return user;
        }
    */
}