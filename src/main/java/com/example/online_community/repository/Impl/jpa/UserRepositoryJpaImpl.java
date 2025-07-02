package com.example.online_community.repository.Impl.jpa;

import com.example.online_community.model.User;
import com.example.online_community.repository.UserRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

/* jap 의존*/
interface SpringDataUserRepository extends JpaRepository<User,Long>{
    Optional<User> findByUsername(String username); // 이메일 기준 찾기
    boolean existsByUsername(String username); // 이미 존재하는지 확인
}

@Repository
@Profile("jpa")
public class UserRepositoryJpaImpl implements UserRepository {

    private final SpringDataUserRepository jpaRepo;

    public UserRepositoryJpaImpl(SpringDataUserRepository jpaRepo) {
        this.jpaRepo = jpaRepo;
    }

    /* 저장 */
    @Override
    public User save(User user) {
        return jpaRepo.save(user);
    }

    /* Id로 조회 */
    @Override
    public Optional<User> findById(Long id) {
        return jpaRepo.findById(id);
    }

    /* 모든 내용 조회 */
    @Override
    public List<User> findAll() {
        return jpaRepo.findAll();
    }
    /* 정보 삭제 */
    @Override
    public void deleteById(Long id) {
        jpaRepo.deleteById(id);
    }

    /* 이미 존재하는 회원인지 확인 */
    @Override
    public boolean existsByUsername(String username) {
        return jpaRepo.existsByUsername(username);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return jpaRepo.findByUsername(username);
    }
}