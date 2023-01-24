package io.nikolamicic21.highperformancepersistenceapp.entity.secondlevelcache.readonly;

import jakarta.persistence.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "cache_readonly_bi_one_to_many_post_comment")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class ReadOnlyBiOneToManyPostComment {

    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Post_SEQ")
//    @SequenceGenerator(name = "Post_SEQ", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "message", nullable = false, length = 50)
    private String message;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private ReadOnlyBiOneToManyPost post;

    public ReadOnlyBiOneToManyPost getPost() {
        return post;
    }

    public void setPost(ReadOnlyBiOneToManyPost post) {
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
