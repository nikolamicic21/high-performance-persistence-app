package io.nikolamicic21.highperformancepersistenceapp.entity.relationships.onetomany.bidirectionalonetomany;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bi_one_to_many_post")
public class BiOneToManyPost {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Post_SEQ")
    @SequenceGenerator(name = "Post_SEQ", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BiOneToManyPostComment> postComments = new ArrayList<>();

    public List<BiOneToManyPostComment> getPostComments() {
        return postComments;
    }

    public void addPostComment(BiOneToManyPostComment postComment) {
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
