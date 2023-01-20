package io.nikolamicic21.highperformancepersistenceapp.entity.relationships.manytomany;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "two_one_to_many_tag")
public class TwoOneToManyTag {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Post_SEQ")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, updatable = false)
    private String name;
    @OneToMany(mappedBy = "tag", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TwoOneToManyPostTag> tagPosts = new LinkedHashSet<>();

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }
}
