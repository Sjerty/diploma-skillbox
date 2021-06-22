package main.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "posts")
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;                     // post id

    @Column(nullable = false, columnDefinition = "org.hibernate.type.NumericBooleanType")
    private boolean isActive;           // is published or not ( 0|1 )

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ModStat moderationStatus;   // moderation status ( default "NEW" )

    @ManyToOne
    @JoinColumn(name = "moderator_id", insertable = false, updatable = false)
    private User moderator;      // mod's id - who changed moderationStatus

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user; // author of the post

    @Column(nullable = false)
    private LocalDateTime time;             // date and time of post publishing

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    @Type(type = "text")
    private String text;

    @Column(nullable = false)
    private int viewCount;              // number of views

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    private List<PostVote> postVotes;

    @ManyToMany(mappedBy = "posts")
    private List<Tag> tags;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    private List<PostComment> postComments;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public ModStat getModerationStatus() {
        return moderationStatus;
    }

    public void setModerationStatus(ModStat moderationStatus) {
        this.moderationStatus = moderationStatus;
    }

    public User getModerator() {
        return moderator;
    }

    public void setModerator(User moderator) {
        this.moderator = moderator;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public List<PostVote> getPostVotes() {
        return postVotes;
    }

    public void setPostVotes(List<PostVote> postVotes) {
        this.postVotes = postVotes;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<PostComment> getPostComments() {
        return postComments;
    }

    public void setPostComments(List<PostComment> postComments) {
        this.postComments = postComments;
    }
}

