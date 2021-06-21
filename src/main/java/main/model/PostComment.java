package main.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "post_comments")
@NoArgsConstructor
@AllArgsConstructor
public class PostComment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;         // comment id

    private int parentId;   // id of parent comment, may be NULL

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post; // post id

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;     // commentator id

    @Column(nullable = false)
    private LocalDateTime time; // date and time of comment

    @Column(nullable = false)
    @Type(type = "text")
    private String text;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
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
