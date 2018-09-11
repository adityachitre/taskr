package com.adichi.taskr.entities.task;

import com.adichi.taskr.entities.BaseEntity;

import javax.persistence.*;

import java.util.UUID;

import static javax.persistence.EnumType.STRING;

@Entity
@Table(name = "task")
public class Task extends BaseEntity {

    private String content;

    @Enumerated(STRING)
    private TaskStatus status;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }
}
