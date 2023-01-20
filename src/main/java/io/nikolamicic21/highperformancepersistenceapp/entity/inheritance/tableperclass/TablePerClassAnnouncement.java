package io.nikolamicic21.highperformancepersistenceapp.entity.inheritance.tableperclass;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "tableperclass_announcement")
public class TablePerClassAnnouncement extends TablePerClassTopic {

    @Temporal(TemporalType.DATE)
    private Date validUntil;

    protected TablePerClassAnnouncement() {
    }

    public TablePerClassAnnouncement(String title, String owner, TablePerClassBoard board, Date validUntil) {
        super(title, owner, board);
        this.validUntil = validUntil;
    }

    public Date getValidUntil() {
        return validUntil;
    }
}
