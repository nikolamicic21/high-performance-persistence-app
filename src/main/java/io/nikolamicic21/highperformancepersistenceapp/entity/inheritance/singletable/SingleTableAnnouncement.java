package io.nikolamicic21.highperformancepersistenceapp.entity.inheritance.singletable;

import jakarta.persistence.Entity;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.util.Date;

@Entity
public class SingleTableAnnouncement extends SingleTableTopic {

    @Temporal(TemporalType.DATE)
    private Date validUntil;

    protected SingleTableAnnouncement() {
    }

    public SingleTableAnnouncement(String title, String owner, SingleTableBoard board, Date validUntil) {
        super(title, owner, board);
        this.validUntil = validUntil;
    }

    public Date getValidUntil() {
        return validUntil;
    }
}
