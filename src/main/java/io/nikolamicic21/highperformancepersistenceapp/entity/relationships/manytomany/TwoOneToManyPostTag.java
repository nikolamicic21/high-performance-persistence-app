package io.nikolamicic21.highperformancepersistenceapp.entity.relationships.manytomany;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "two_one_to_many_post_tag")
public class TwoOneToManyPostTag {

    @EmbeddedId
    private TwoOneToManyPostTagId id;
    @ManyToOne
    @MapsId("post_id")
    private TwoOneToManyPost post;
    @ManyToOne
    @MapsId("tag_id")
    private TwoOneToManyTag tag;

    protected TwoOneToManyPostTag() {
    }

    public TwoOneToManyPostTag(TwoOneToManyPost post, TwoOneToManyTag tag) {
        this.post = post;
        this.tag = tag;
        this.id = new TwoOneToManyPostTagId(post.getId(), tag.getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TwoOneToManyPostTag that = (TwoOneToManyPostTag) o;
        return Objects.equals(post, that.post) && Objects.equals(tag, that.tag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(post, tag);
    }
}
