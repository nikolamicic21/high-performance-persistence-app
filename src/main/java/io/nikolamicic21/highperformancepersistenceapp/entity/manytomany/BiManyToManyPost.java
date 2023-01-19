package io.nikolamicic21.highperformancepersistenceapp.entity.manytomany;

import jakarta.persistence.*;
import org.hibernate.Hibernate;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "bi_many_to_many_post")
public class BiManyToManyPost {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Post_SEQ")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, updatable = false)
    private String name;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "bi_many_to_many_post_biManyToManyTags",
            joinColumns = @JoinColumn(name = "biManyToManyPost_id"),
            inverseJoinColumns = @JoinColumn(name = "biManyToManyTags_id"))
    private Set<BiManyToManyTag> tags = new LinkedHashSet<>();

    private BiManyToManyPost() {
    }

    public BiManyToManyPost(String name) {
        this.name = name;
    }

    public Set<BiManyToManyTag> getTags() {
        return tags;
    }

    public void setTags(Set<BiManyToManyTag> tags) {
        this.tags = tags;
    }

    public void addTag(BiManyToManyTag tag) {
        tags.add(tag);
        tag.getPosts().add(this);
    }

    public void removeTag(BiManyToManyTag tag) {
        tags.remove(tag);
        tag.getPosts().remove(this);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        BiManyToManyPost that = (BiManyToManyPost) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
