package io.nikolamicic21.highperformancepersistenceapp.entity.concurrencycontrol.optimisticlocks;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "optimisticlocks_bi_one_to_many_post")
public class OptimisticLocksBiOneToManyPost {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Post_SEQ")
    @SequenceGenerator(name = "Post_SEQ", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @Version
    private int version;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OptimisticLocksBiOneToManyPostComment> postComments = new ArrayList<>();

    public List<OptimisticLocksBiOneToManyPostComment> getPostComments() {
        return postComments;
    }

    public void addPostComment(OptimisticLocksBiOneToManyPostComment postComment) {
        postComments.add(postComment);
        postComment.setPost(this);
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
