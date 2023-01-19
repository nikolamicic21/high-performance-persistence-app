package io.nikolamicic21.highperformancepersistenceapp.entity.onetomany.unidirectionalonetomanyset;

import jakarta.persistence.*;

import java.nio.ByteBuffer;
import java.util.Base64;
import java.util.Objects;

@Entity
@Table(name = "uni_one_to_many_post_comment")
public class UniOneToManySetPostComment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Post_SEQ")
    @SequenceGenerator(name = "Post_SEQ", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "message", nullable = false, length = 50)
    private String message;

    private String slug;

    public UniOneToManySetPostComment() {
        final var bytes = new byte[8];
        ByteBuffer.wrap(bytes).putDouble(Math.random());
        slug = Base64.getEncoder().encodeToString(bytes);
    }

    public UniOneToManySetPostComment(String message) {
        this();
        this.message = message;
    }

    public String getSlug() {
        return slug;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UniOneToManySetPostComment that = (UniOneToManySetPostComment) o;
        return Objects.equals(slug, that.slug);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(slug);
    }
}
