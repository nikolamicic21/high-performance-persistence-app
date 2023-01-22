package io.nikolamicic21.highperformancepersistenceapp.entity.dto;

public class PostCommentSummary {

    private Long id;
    private String name;
    private String review;

    public PostCommentSummary() {
    }

    public PostCommentSummary(Long id, String name, String review) {
        this.id = id;
        this.name = name;
        this.review = review;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getReview() {
        return review;
    }

    @Override
    public String toString() {
        return "PostCommentSummary{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", review='" + review + '\'' +
                '}';
    }
}
