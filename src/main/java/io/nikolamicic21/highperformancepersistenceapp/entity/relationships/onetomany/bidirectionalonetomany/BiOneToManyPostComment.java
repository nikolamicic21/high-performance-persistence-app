package io.nikolamicic21.highperformancepersistenceapp.entity.relationships.onetomany.bidirectionalonetomany;

import jakarta.persistence.*;

@Entity
@Table(name = "bi_one_to_many_post_comment")
public class BiOneToManyPostComment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Post_SEQ")
    @SequenceGenerator(name = "Post_SEQ", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "message", nullable = false, length = 50)
    private String message;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private BiOneToManyPost post;

    public BiOneToManyPost getPost() {
        return post;
    }

    public void setPost(BiOneToManyPost post) {
        this.post = post;
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
}
