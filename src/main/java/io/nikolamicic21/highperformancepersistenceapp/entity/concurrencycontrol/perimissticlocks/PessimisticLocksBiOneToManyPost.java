package io.nikolamicic21.highperformancepersistenceapp.entity.concurrencycontrol.perimissticlocks;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pessimisticlocks_bi_one_to_many_post")
public class PessimisticLocksBiOneToManyPost {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Post_SEQ")
    @SequenceGenerator(name = "Post_SEQ", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PessimisticLocksBiOneToManyPostComment> postComments = new ArrayList<>();

    public List<PessimisticLocksBiOneToManyPostComment> getPostComments() {
        return postComments;
    }

    public void addPostComment(PessimisticLocksBiOneToManyPostComment postComment) {
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
