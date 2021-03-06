package main.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "post_comments")
@NoArgsConstructor
@AllArgsConstructor
public class PostComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;         // comment id

    private Integer parentId;   // id of parent comment, may be NULL

    @ManyToOne
    @JoinColumn(name = "post_id")
    @JsonIgnoreProperties({"moderator", "user", "postVotes", "tags", "postComments"})
    private Post post; // post id

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"posts", "votes", "userCommentaries"})
    private User user;     // commentator id

    @Column(nullable = false)
    private LocalDateTime time; // date and time of comment

    @Column(nullable = false, columnDefinition = "text")
    private String text;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
