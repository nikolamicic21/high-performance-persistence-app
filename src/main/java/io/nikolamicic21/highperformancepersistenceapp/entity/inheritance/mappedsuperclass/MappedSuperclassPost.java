package io.nikolamicic21.highperformancepersistenceapp.entity.inheritance.mappedsuperclass;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "mappersuperclass_post")
public class MappedSuperclassPost extends MappedSupperclassTopic {

    private String content;

    protected MappedSuperclassPost() {
    }

    public MappedSuperclassPost(String title, String owner, MappedSuperclassBoard board, String content) {
        super(title, owner, board);
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
