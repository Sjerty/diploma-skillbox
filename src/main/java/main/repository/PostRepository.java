package main.repository;

import main.model.ModStat;
import main.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends CrudRepository<Post, Integer> {
    Page<Post> findAll(Pageable pageable);

    Page<Post> findByModerationStatusAndIsActive(ModStat modStat, boolean isActive, Pageable pageable);
}
