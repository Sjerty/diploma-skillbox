package main.dto;

import main.model.PostComment;
import main.model.PostVote;
import main.model.User;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class MainPagePost {

    private int id;
    private Timestamp timestamp;
    private User user;
    private String title;
    private String announce;
    private long likeCount = 0;
    private long dislikeCount = 0;
    private long commentCount = 0;
    private int viewCount = 0;

    private final static int ANNOUNCE_MAX_SIZE = 149;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return timestamp.toLocalDateTime();
    }

    public void setTimestamp(LocalDateTime time) {
        this.timestamp = Timestamp.valueOf(time);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAnnounce() {
        return announce;
    }

    public void setAnnounce(String text) {
        int textSize = Math.min(ANNOUNCE_MAX_SIZE, text.length());
        this.announce = text.substring(0, textSize) + "...";
    }

    public long getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(List<PostVote> votes) {
        this.likeCount = votes.stream()
                .filter(v -> v.isValue())
                .count();
    }

    public long getDislikeCount() {
        return dislikeCount;
    }

    public void setDislikeCount(List<PostVote> votes) {
        this.dislikeCount = votes.stream()
                .filter(v -> !v.isValue())
                .count();
    }

    public long getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(List<PostComment> postComments) {
        this.commentCount = postComments.size();
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public static int getAnnounceMaxSize() {
        return ANNOUNCE_MAX_SIZE;
    }


}
