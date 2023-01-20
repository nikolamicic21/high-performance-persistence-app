package io.nikolamicic21.highperformancepersistenceapp.entity.relationships.onetomany.unidirectionalonetomany;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "uni_one_to_many_post")
public class UniOneToManyPost {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Post_SEQ")
    @SequenceGenerator(name = "Post_SEQ", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @OrderColumn(name = "order_column_index")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UniOneToManyPostComment> postComments = new ArrayList<>();

    public List<UniOneToManyPostComment> getPostComments() {
        return postComments;
    }

    public void addPostComment(UniOneToManyPostComment postComment) {
        postComments.add(postComment);
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
