package io.nikolamicic21.highperformancepersistenceapp.entity.relationships.manytomany;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "two_one_to_many_post")
public class TwoOneToManyPost {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Post_SEQ")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, updatable = false)
    private String name;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TwoOneToManyPostTag> postTags = new LinkedHashSet<>();

    public Set<TwoOneToManyPostTag> getPostTags() {
        return postTags;
    }

    public void setPostTags(Set<TwoOneToManyPostTag> postTags) {
        this.postTags = postTags;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
