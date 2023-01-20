package io.nikolamicic21.highperformancepersistenceapp.entity.inheritance.mappedsuperclass;

import jakarta.persistence.*;

@Entity
@Table(name = "mappersuperclass_announcement_statistics")
public class MappedSuperclassAnnouncementStatistics {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Post_SEQ")
    @SequenceGenerator(name = "Post_SEQ", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "id")
    @MapsId
    private MappedSuperclassAnnouncement announcement;

    @Column(name = "views")
    private Long views = 0L;

    protected MappedSuperclassAnnouncementStatistics() {
    }

    public MappedSuperclassAnnouncementStatistics(MappedSuperclassAnnouncement announcement) {
        this.announcement = announcement;
    }

    public void incrementViews() {
        this.views = this.views + 1;
    }

    public Long getViews() {
        return views;
    }

    public MappedSuperclassAnnouncement getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(MappedSuperclassAnnouncement announcement) {
        this.announcement = announcement;
    }

    public Long getId() {
        return id;
    }


}
