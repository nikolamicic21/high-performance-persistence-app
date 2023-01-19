package io.nikolamicic21.highperformancepersistenceapp.entity.manytomany;

import jakarta.persistence.*;

@Entity
@Table(name = "uni_many_to_many_set_tag")
public class UniManyToManySetTag {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Post_SEQ")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, updatable = false)
    private String name;

    public UniManyToManySetTag() {
    }

    public UniManyToManySetTag(String name) {
        this.name = name;
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
