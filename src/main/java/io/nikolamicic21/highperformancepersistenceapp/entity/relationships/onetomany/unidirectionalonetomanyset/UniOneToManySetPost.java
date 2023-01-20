package io.nikolamicic21.highperformancepersistenceapp.entity.relationships.onetomany.unidirectionalonetomanyset;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "uni_one_to_many_post")
public class UniOneToManySetPost {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Post_SEQ")
    @SequenceGenerator(name = "Post_SEQ", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "uni_one_to_many_set_post_id")
    private Set<UniOneToManySetPostComment> uniOneToManySetPostComments = new HashSet<>();

    public Set<UniOneToManySetPostComment> getUniOneToManySetPostComments() {
        return uniOneToManySetPostComments;
    }

    public void setUniOneToManySetPostComments(Set<UniOneToManySetPostComment> uniOneToManySetPostComments) {
        this.uniOneToManySetPostComments = uniOneToManySetPostComments;
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
