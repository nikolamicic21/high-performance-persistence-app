package io.nikolamicic21.highperformancepersistenceapp.entity.inheritance.mappedsuperclass;

import jakarta.persistence.*;

@Entity
@Table(name = "mappersuperclass_post_statistics")
public class MappedSuperclassPostStatistics {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Post_SEQ")
    @SequenceGenerator(name = "Post_SEQ", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "id")
    @MapsId
    private MappedSuperclassPost post;

    @Column(name = "views")
    private Long views = 0L;

    protected MappedSuperclassPostStatistics() {
    }

    public MappedSuperclassPostStatistics(MappedSuperclassPost post) {
        this.post = post;
    }

    public void incrementViews() {
        this.views = this.views + 1;
    }

    public Long getViews() {
        return views;
    }

    public MappedSuperclassPost getPost() {
        return post;
    }

    public void setPost(MappedSuperclassPost post) {
        this.post = post;
    }

    public Long getId() {
        return id;
    }


}
