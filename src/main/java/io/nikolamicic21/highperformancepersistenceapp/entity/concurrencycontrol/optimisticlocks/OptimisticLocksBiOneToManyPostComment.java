package io.nikolamicic21.highperformancepersistenceapp.entity.concurrencycontrol.optimisticlocks;

import jakarta.persistence.*;

@Entity
@Table(name = "optimisticlocks_bi_one_to_many_post_comment")
public class OptimisticLocksBiOneToManyPostComment {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "message", nullable = false, length = 50)
    private String message;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private OptimisticLocksBiOneToManyPost post;

    public OptimisticLocksBiOneToManyPost getPost() {
        return post;
    }

    public void setPost(OptimisticLocksBiOneToManyPost post) {
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
