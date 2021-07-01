package main.service;

import main.model.Tag;
import main.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    public List<Tag> getTagList(){
        Iterable<Tag> tagIterable = tagRepository.findAll();
        List<Tag> allTags = new ArrayList<>();
        tagIterable.forEach(allTags::add);
        return allTags;
    }
}
