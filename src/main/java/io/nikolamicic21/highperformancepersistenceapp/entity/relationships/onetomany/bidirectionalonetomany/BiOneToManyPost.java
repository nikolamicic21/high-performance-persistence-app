package io.nikolamicic21.highperformancepersistenceapp.entity.relationships.onetomany.bidirectionalonetomany;

import io.nikolamicic21.highperformancepersistenceapp.entity.dto.PostCommentSummary;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@NamedNativeQuery(
        name = "PostCommentSummary",
        query = "select p.id as id, p.name as title, c.message as review " +
                "from bi_one_to_many_post_comment c " +
                "join bi_one_to_many_post p on c.post_id=p.id " +
                "order by p.id",
        resultSetMapping = "PostCommentSummaryMapping"
)
@SqlResultSetMapping(
        name = "PostCommentSummaryMapping",
        classes = @ConstructorResult(
                targetClass = PostCommentSummary.class,
                columns = {
                        @ColumnResult(name = "id"),
                        @ColumnResult(name = "title"),
                        @ColumnResult(name = "review")
                }
        )
)
@Entity
@Table(name = "bi_one_to_many_post")
public class BiOneToManyPost {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Post_SEQ")
    @SequenceGenerator(name = "Post_SEQ", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BiOneToManyPostComment> postComments = new ArrayList<>();

    public List<BiOneToManyPostComment> getPostComments() {
        return postComments;
    }

    public void addPostComment(BiOneToManyPostComment postComment) {
        postComments.add(postComment);
        postComment.setPost(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
