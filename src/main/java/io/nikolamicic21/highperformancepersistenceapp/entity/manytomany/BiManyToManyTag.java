package io.nikolamicic21.highperformancepersistenceapp.entity.manytomany;

import jakarta.persistence.*;
import org.hibernate.Hibernate;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "bi_many_to_many_tag")
public class BiManyToManyTag {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Post_SEQ")
    @SequenceGenerator(name = "Post_SEQ")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, updatable = false)
    private String name;

    @ManyToMany(mappedBy = "tags")
    private Set<BiManyToManyPost> posts = new LinkedHashSet<>();

    private BiManyToManyTag() {
    }

    public BiManyToManyTag(String name) {
        this.name = name;
    }

    public Set<BiManyToManyPost> getPosts() {
        return posts;
    }

    public void setPosts(Set<BiManyToManyPost> posts) {
        this.posts = posts;
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
        BiManyToManyTag that = (BiManyToManyTag) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
