package com.example.online_community.repository.Impl.memory;

import com.example.online_community.model.User;
import com.example.online_community.repository.UserRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@Profile("memory")
public class UserRepositoryMemoryImpl implements UserRepository {

    private final Map<Long,User> store = new HashMap<>();
    private Long nextId = 1L;

    /* 저장 */
    @Override
    public User save(User user) {
        if(user.getId() == null){
            user.setId(nextId++);
        }
        store.put(user.getId(), user);
        return user;
    }

    /* Id로 조회 */
    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    /* 모든 내용 조회 */
    @Override
    public List<User> findAll() {
        return new ArrayList<>(store.values());
    }
    /* 정보 삭제 */
    @Override
    public void deleteById(Long id) {
        store.remove(id);
    }

    /* 이미 존재하는 회원인지 확인 */
    @Override
    public boolean existsByUsername(String username) {
        return store.values().stream()
                .anyMatch(u -> u.getUsername().equals(username));
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return store.values().stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst();
    }
}