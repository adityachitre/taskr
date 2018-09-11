package com.adichi.taskr.services;

import com.adichi.taskr.dtos.TaskDto;
import com.adichi.taskr.entities.task.Task;

import java.util.List;
import java.util.UUID;

public interface TaskService {

    List<Task> getAllTasks();

    Task getTaskById(UUID id);

    Task add(Task task);

    Task save(Task model);

    Task update(TaskDto dto, Task model);
}
