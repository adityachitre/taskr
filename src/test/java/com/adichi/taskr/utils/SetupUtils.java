package com.adichi.taskr.utils;

import com.adichi.taskr.dtos.TaskDto;
import com.adichi.taskr.entities.task.Task;

import java.time.Instant;
import java.util.UUID;

import static com.adichi.taskr.entities.task.TaskStatus.NEW;
import static com.adichi.taskr.entities.task.TaskStatus.PENDING;
import static java.time.Instant.now;
import static java.util.UUID.fromString;

public final class SetupUtils {

    public static final UUID ID1 = fromString("c6bd2829-8bce-4a45-b62a-52db43757894");
    public static final UUID ID2 = fromString("20348744-4b86-48a5-af97-48793611a25f");

    public static final String MODEL_CONTENT = "Make the world a slightly better place than yesterday";
    public static final String DTO_CONTENT = "Repeat small meaningful actions everyday";

    public static final Instant NOW = now();

    public static final int OCCURS_ONCE = 1;

    private SetupUtils() {

        // No instances allowed
    }

    public static Task createTask(UUID uuid) {

        Task task = new Task();
        task.setId(uuid);
        task.setContent(MODEL_CONTENT);
        task.setStatus(NEW);
        task.setCreatedAt(NOW);
        task.setUpdatedAt(NOW);

        return task;
    }

    public static TaskDto createTaskDto(UUID uuid) {

        TaskDto dto = new TaskDto();
        dto.setId(uuid);
        dto.setStatus(PENDING.name());
        dto.setContent(DTO_CONTENT);
        dto.setCreatedAt(NOW);
        dto.setUpdatedAt(NOW);

        return dto;
    }
}
