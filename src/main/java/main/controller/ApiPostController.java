package main.controller;

import main.Main;
import main.dto.MainPagePost;
import main.service.PostService;
import main.model.Post;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ApiPostController {
    @Autowired
    private PostService postService;

    @Autowired
    private ModelMapper modelMapper;

    private final static String sortDir = "desc";
    @GetMapping("/api/post")
    public ResponseEntity getPosts(@RequestParam(required = false, defaultValue = "0") Integer offset,
                                   @RequestParam(required = false, defaultValue = "10") Integer limit,
                                   @RequestParam(required = false, defaultValue = "recent") String mode,
                                   Model model){

        List<Post> fullPosts = postService.getPostsList(offset, limit, sortDir);

        List<MainPagePost> postsForMainPage = fullPosts.stream()
                .map(this::convertPostToDto)
                .collect(Collectors.toList());
        sortList(postsForMainPage, mode);

        model.addAttribute("count", postsForMainPage.size());
        model.addAttribute("posts", postsForMainPage);

        return new ResponseEntity(model, HttpStatus.OK);
    }
    //@GetMapping("/api/post/search")

    //@GetMapping("/api/post/byDate")

    //@GetMapping("/api/post/byTag")

    //@GetMapping("/api/post/{ID}")

    //@GetMapping("/api/post/moderation")

    //@GetMapping("/api/post/my")

    //@PostMapping("/api/post")

    //@PutMapping("/api/post/{ID}")

    //@PostMapping("/api/post/like")

    //@PostMapping("/api/post/dislike")

    private MainPagePost convertPostToDto(Post post){
        MainPagePost mpPost = modelMapper.map(post, MainPagePost.class);
        mpPost.setTimestamp(post.getTime());
        mpPost.setAnnounce(post.getText());
        mpPost.setLikeCount(post.getPostVotes());
        mpPost.setDislikeCount(post.getPostVotes());
        return mpPost;
    }

    private void sortList(List<MainPagePost> unsortedList, String mode){
        switch (mode) {
            case "recent" -> unsortedList.sort(Comparator.comparing(MainPagePost::getDateTime).reversed());
            case "popular" -> unsortedList.sort((p1, p2) -> (int) (p2.getCommentCount() - p1.getCommentCount()));
            case "best" -> unsortedList.sort((p1, p2) -> (int) (p2.getLikeCount() - p1.getLikeCount()));
            case "early" -> unsortedList.sort(Comparator.comparing(MainPagePost::getDateTime));
        }
    }
}
