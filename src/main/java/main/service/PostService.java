package main.service;

import main.model.ModStat;
import main.model.Post;
import main.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private static final boolean IS_ACTIVE = true;

    @Autowired
    private PostRepository postRepository;

    public List<Post> getPostsList(int offset, int limit, String sortDir){

        Pageable pageable = PageRequest.of(offset, limit, Sort.Direction.fromString(sortDir), "isActive");
        Page<Post> allPosts = postRepository.findByModerationStatusAndIsActive(ModStat.ACCEPTED, IS_ACTIVE, pageable);
        return allPosts.getContent();
    }


}
