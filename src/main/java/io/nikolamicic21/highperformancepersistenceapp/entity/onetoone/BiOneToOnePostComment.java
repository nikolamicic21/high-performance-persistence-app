package io.nikolamicic21.highperformancepersistenceapp.entity.onetoone;

import jakarta.persistence.*;

@Entity
@Table(name = "bi_one_to_one_post_comment")
public class BiOneToOnePostComment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Post_SEQ")
    @SequenceGenerator(name = "Post_SEQ", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "message", nullable = false, length = 50)
    private String message;

    @OneToOne
    @JoinColumn(name = "post_id")
    private BiOneToOnePost post;

    public BiOneToOnePost getPost() {
        return post;
    }

    public void setPost(BiOneToOnePost post) {
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
