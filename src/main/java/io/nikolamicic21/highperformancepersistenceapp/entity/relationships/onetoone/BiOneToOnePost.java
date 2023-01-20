package io.nikolamicic21.highperformancepersistenceapp.entity.relationships.onetoone;

import jakarta.persistence.*;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

@Entity
@Table(name = "bi_one_to_one_post")
public class BiOneToOnePost {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Post_SEQ")
    @SequenceGenerator(name = "Post_SEQ", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @OneToOne(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    private BiOneToOnePostComment comment;

    public BiOneToOnePostComment getComment() {
        return comment;
    }

    public void setComment(BiOneToOnePostComment comment) {
        if (comment == null) {
            if (this.comment != null) this.comment.setPost(null);
        } else {
            comment.setPost(this);
        }
        this.comment = comment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
