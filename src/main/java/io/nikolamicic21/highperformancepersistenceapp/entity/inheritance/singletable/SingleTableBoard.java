package io.nikolamicic21.highperformancepersistenceapp.entity.inheritance.singletable;

import jakarta.persistence.*;

@Entity
@Table(name = "singletable_board")
public class SingleTableBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Post_SEQ")
    @SequenceGenerator(name = "Post_SEQ", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    public SingleTableBoard() {
    }

    public SingleTableBoard(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }
}
