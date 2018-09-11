package com.adichi.taskr.transformers;

import com.adichi.taskr.dtos.TaskDto;
import com.adichi.taskr.entities.task.Task;

import static com.adichi.taskr.entities.task.TaskStatus.NEW;
import static com.adichi.taskr.entities.task.TaskStatus.valueOf;
import static org.springframework.util.StringUtils.isEmpty;

public final class TaskTransformer {

    public static TaskDto transformToDto(Task task) {

        if (task == null) {

            return null;
        }

        TaskDto dto = new TaskDto();
        dto.setId(task.getId());
        dto.setContent(task.getContent());
        dto.setStatus(task.getStatus().name());
        dto.setCreatedAt(task.getCreatedAt());
        dto.setUpdatedAt(task.getUpdatedAt());

        return dto;
    }

    public static Task transformToModel(TaskDto taskDto) {

        if (taskDto == null) {

            return null;
        }

        Task model = new Task();
        model.setContent(taskDto.getContent());
        String status = taskDto.getStatus();
        if (isEmpty(status)) {
            model.setStatus(NEW);
        } else {
            model.setStatus(valueOf(status));
        }

        return model;
    }
}
