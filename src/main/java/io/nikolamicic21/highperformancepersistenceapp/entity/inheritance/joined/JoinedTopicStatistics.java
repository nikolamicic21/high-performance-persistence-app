package io.nikolamicic21.highperformancepersistenceapp.entity.inheritance.joined;

import jakarta.persistence.*;

@Entity
@Table(name = "joined_topic_statistics")
public class JoinedTopicStatistics {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Post_SEQ")
    @SequenceGenerator(name = "Post_SEQ", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "id")
    @MapsId
    private JoinedTopic topic;

    @Column(name = "views")
    private Long views = 0L;

    protected JoinedTopicStatistics() {
    }

    public JoinedTopicStatistics(JoinedTopic topic) {
        this.topic = topic;
    }

    public void incrementViews() {
        this.views = this.views + 1;
    }

    public Long getViews() {
        return views;
    }

    public JoinedTopic getTopic() {
        return topic;
    }

    public void setTopic(JoinedTopic topic) {
        this.topic = topic;
    }

    public Long getId() {
        return id;
    }


}
