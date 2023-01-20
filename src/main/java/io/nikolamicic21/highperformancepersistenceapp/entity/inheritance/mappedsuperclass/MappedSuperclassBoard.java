package io.nikolamicic21.highperformancepersistenceapp.entity.inheritance.mappedsuperclass;

import jakarta.persistence.*;

@Entity
@Table(name = "mappedsuperclass_board")
public class MappedSuperclassBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Post_SEQ")
    @SequenceGenerator(name = "Post_SEQ", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    public MappedSuperclassBoard() {
    }

    public MappedSuperclassBoard(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }
}
