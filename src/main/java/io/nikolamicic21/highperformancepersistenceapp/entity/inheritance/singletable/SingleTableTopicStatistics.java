package io.nikolamicic21.highperformancepersistenceapp.entity.inheritance.singletable;

import jakarta.persistence.*;

@Entity
@Table(name = "singletable_topic_statistics")
public class SingleTableTopicStatistics {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Post_SEQ")
    @SequenceGenerator(name = "Post_SEQ", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "id")
    @MapsId
    private SingleTableTopic topic;

    @Column(name = "views")
    private Long views = 0L;

    protected SingleTableTopicStatistics() {
    }

    public SingleTableTopicStatistics(SingleTableTopic topic) {
        this.topic = topic;
    }

    public void incrementViews() {
        this.views = this.views + 1;
    }

    public Long getViews() {
        return views;
    }

    public SingleTableTopic getTopic() {
        return topic;
    }

    public void setTopic(SingleTableTopic topic) {
        this.topic = topic;
    }

    public Long getId() {
        return id;
    }


}
