package io.nikolamicic21.highperformancepersistenceapp.entity.inheritance.joined;

import jakarta.persistence.*;

@Entity
@Table(name = "joined_board")
public class JoinedBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Post_SEQ")
    @SequenceGenerator(name = "Post_SEQ", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    public JoinedBoard() {
    }

    public JoinedBoard(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }
}
