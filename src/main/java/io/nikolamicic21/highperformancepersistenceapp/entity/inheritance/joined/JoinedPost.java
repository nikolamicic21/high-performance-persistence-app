package io.nikolamicic21.highperformancepersistenceapp.entity.inheritance.joined;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "joined_post")
@PrimaryKeyJoinColumn(name = "topic_id")
public class JoinedPost extends JoinedTopic {

    private String content;

    protected JoinedPost() {
    }

    public JoinedPost(String title, String owner, JoinedBoard board, String content) {
        super(title, owner, board);
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
