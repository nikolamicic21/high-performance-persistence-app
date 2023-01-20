package io.nikolamicic21.highperformancepersistenceapp.entity.relationships.manytomany;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "uni_many_to_many_set_post")
public class UniManyToManySetPost {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Post_SEQ")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, updatable = false)
    private String name;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "uni_many_to_many_list_post_uniManyToManySetTags",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<UniManyToManySetTag> tags = new HashSet<>();

    private UniManyToManySetPost() {
    }

    public UniManyToManySetPost(String name) {
        this.name = name;
    }

    public Set<UniManyToManySetTag> getTags() {
        return tags;
    }

    public void setTags(Set<UniManyToManySetTag> tags) {
        this.tags = tags;
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
