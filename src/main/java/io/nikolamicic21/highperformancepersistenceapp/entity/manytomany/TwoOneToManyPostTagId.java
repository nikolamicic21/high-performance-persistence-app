package io.nikolamicic21.highperformancepersistenceapp.entity.manytomany;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TwoOneToManyPostTagId implements Serializable {
    @Column(name = "post_id")
    private Long postId;

    @Column(name = "tag_id")
    private Long tag_id;

    public TwoOneToManyPostTagId() {
    }

    public TwoOneToManyPostTagId(Long postId, Long tag_id) {
        this.postId = postId;
        this.tag_id = tag_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TwoOneToManyPostTagId that = (TwoOneToManyPostTagId) o;
        return Objects.equals(postId, that.postId) && Objects.equals(tag_id, that.tag_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId, tag_id);
    }

    public Long getTag_id() {
        return tag_id;
    }

    public Long getPostId() {
        return postId;
    }
}
