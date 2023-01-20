package io.nikolamicic21.highperformancepersistenceapp.entity.inheritance.singletable;

import jakarta.persistence.Entity;

@Entity
public class SingleTablePost extends SingleTableTopic {

    private String content;

    protected SingleTablePost() {
    }

    public SingleTablePost(String title, String owner, SingleTableBoard board, String content) {
        super(title, owner, board);
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
