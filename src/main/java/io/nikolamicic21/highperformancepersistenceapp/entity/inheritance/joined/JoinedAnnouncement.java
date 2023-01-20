package io.nikolamicic21.highperformancepersistenceapp.entity.inheritance.joined;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "joined_announcement")
@PrimaryKeyJoinColumn(name = "topic_id")
public class JoinedAnnouncement extends JoinedTopic {

    @Temporal(TemporalType.DATE)
    private Date validUntil;

    protected JoinedAnnouncement() {
    }

    public JoinedAnnouncement(String title, String owner, JoinedBoard board, Date validUntil) {
        super(title, owner, board);
        this.validUntil = validUntil;
    }

    public Date getValidUntil() {
        return validUntil;
    }
}
