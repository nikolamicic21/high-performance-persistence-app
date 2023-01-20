package io.nikolamicic21.highperformancepersistenceapp.entity.inheritance.mappedsuperclass;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.util.Date;

@Entity
@Table(name = "mappersuperclass_announcement")
public class MappedSuperclassAnnouncement extends MappedSupperclassTopic {

    @Temporal(TemporalType.DATE)
    private Date validUntil;

    protected MappedSuperclassAnnouncement() {
    }

    public MappedSuperclassAnnouncement(String title, String owner, MappedSuperclassBoard board, Date validUntil) {
        super(title, owner, board);
        this.validUntil = validUntil;
    }

    public Date getValidUntil() {
        return validUntil;
    }
}
