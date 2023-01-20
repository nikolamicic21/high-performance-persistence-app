package io.nikolamicic21.highperformancepersistenceapp.entity.inheritance.tableperclass;

import jakarta.persistence.*;

@Entity
@Table(name = "tableperclass_board")
public class TablePerClassBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Post_SEQ")
    @SequenceGenerator(name = "Post_SEQ", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    public TablePerClassBoard() {
    }

    public TablePerClassBoard(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }
}
