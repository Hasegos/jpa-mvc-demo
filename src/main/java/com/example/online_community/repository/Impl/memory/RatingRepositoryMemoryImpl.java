package com.example.online_community.repository.Impl.memory;

import com.example.online_community.model.Rating;
import com.example.online_community.repository.RatingRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@Profile("memory")
public class RatingRepositoryMemoryImpl implements RatingRepository {

    private final Map<Long,Rating> store = new HashMap<>();
    private Long nextId = 1L;

    /* 저장 */
    @Override
    public Rating save(Rating rating) {
        if(rating.getId() == null){
            rating.setId(nextId++);
        }
        store.put(rating.getId(), rating);
        return rating;
    }

    /* ID 기준 찾기 */
    @Override
    public Optional<Rating> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    /* CourseId 기준찾기 */
    @Override
    public List<Rating> findByCourseId(Long courseId) {
        List<Rating> list = new ArrayList<>();
        for(Rating r : store.values()){
            if(r.getCourse().getId().equals(courseId)){
                list.add(r);
            }
        }
        return list;
    }

    /* 삭제 */
    @Override
    public void deleteId(Long id) {
        store.remove(id);
    }
}