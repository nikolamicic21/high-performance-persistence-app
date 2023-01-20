package io.nikolamicic21.highperformancepersistenceapp.entity.inheritance.tableperclass;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tableperclass_post")
public class TablePerClassPost extends TablePerClassTopic {

    private String content;

    protected TablePerClassPost() {
    }

    public TablePerClassPost(String title, String owner, TablePerClassBoard board, String content) {
        super(title, owner, board);
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
