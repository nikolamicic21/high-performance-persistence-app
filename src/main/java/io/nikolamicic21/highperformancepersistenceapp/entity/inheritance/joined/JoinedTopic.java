package io.nikolamicic21.highperformancepersistenceapp.entity.inheritance.joined;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "joined_topic")
@Inheritance(strategy = InheritanceType.JOINED)
public class JoinedTopic {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Post_SEQ")
    @SequenceGenerator(name = "Post_SEQ", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "owner", nullable = false)
    private String owner;

    @Temporal(TemporalType.DATE)
    @Column(name = "created_on", nullable = false)
    private Date createdOn = new Date();

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", nullable = false)
    private JoinedBoard board;

    protected JoinedTopic() {
    }

    public JoinedTopic(String title, String owner, JoinedBoard board) {
        this.title = title;
        this.owner = owner;
        this.board = board;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public String getOwner() {
        return owner;
    }

    public String getTitle() {
        return title;
    }

    public Long getId() {
        return id;
    }
}
