package main.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tag2post")
@NoArgsConstructor
@AllArgsConstructor
public class Tag2Post {

    @EmbeddedId
    Tag2PostKey id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("postId")
    @JoinColumn(name = "post_id")
    @JsonIgnoreProperties({"moderator", "user", "postVotes", "tags", "postComments"})
    Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("tagId")
    @JoinColumn(name = "tag_id")
    @JsonIgnoreProperties("posts")
    Tag tag;

    public Tag2PostKey getId() {
        return id;
    }

    public void setId(Tag2PostKey id) {
        this.id = id;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    @Embeddable
    class Tag2PostKey implements Serializable {
        @Column( name = "post_id")
        int postId;

        @Column( name = "tag_id")
        int tagId;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Tag2PostKey that = (Tag2PostKey) o;
            return postId == that.postId && tagId == that.tagId;
        }

        @Override
        public int hashCode() {
            return Objects.hash(postId, tagId);
        }
    }
}

