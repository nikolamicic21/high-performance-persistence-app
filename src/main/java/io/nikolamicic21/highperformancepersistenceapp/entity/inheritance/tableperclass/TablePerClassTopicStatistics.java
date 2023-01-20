package io.nikolamicic21.highperformancepersistenceapp.entity.inheritance.tableperclass;

import jakarta.persistence.*;

@Entity
@Table(name = "tableperclass_topic_statistics")
public class TablePerClassTopicStatistics {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Post_SEQ")
    @SequenceGenerator(name = "Post_SEQ", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    @MapsId
    private TablePerClassTopic topic;

    @Column(name = "views")
    private Long views = 0L;

    protected TablePerClassTopicStatistics() {
    }

    public TablePerClassTopicStatistics(TablePerClassTopic topic) {
        this.topic = topic;
    }

    public void incrementViews() {
        this.views = this.views + 1;
    }

    public Long getViews() {
        return views;
    }

    public TablePerClassTopic getTopic() {
        return topic;
    }

    public void setTopic(TablePerClassTopic topic) {
        this.topic = topic;
    }

    public Long getId() {
        return id;
    }


}
