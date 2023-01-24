package io.nikolamicic21.highperformancepersistenceapp.entity.secondlevelcache.readonly;

import jakarta.persistence.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.ArrayList;
import java.util.List;

@Entity
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@Table(name = "cache_readonly_bi_one_to_many_post")
public class ReadOnlyBiOneToManyPost {
    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Post_SEQ")
//    @SequenceGenerator(name = "Post_SEQ", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
    private List<ReadOnlyBiOneToManyPostComment> postComments = new ArrayList<>();

    public List<ReadOnlyBiOneToManyPostComment> getPostComments() {
        return postComments;
    }

    public void addPostComment(ReadOnlyBiOneToManyPostComment postComment) {
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
