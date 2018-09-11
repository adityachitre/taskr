package com.adichi.taskr.services.impl;

import com.adichi.taskr.dtos.TaskDto;
import com.adichi.taskr.entities.task.Task;
import com.adichi.taskr.entities.task.TaskStatus;
import com.adichi.taskr.repositories.TaskRepository;
import com.adichi.taskr.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.adichi.taskr.entities.task.TaskStatus.valueOf;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task getTaskById(UUID id) {
        Optional<Task> optNote = taskRepository.findById(id);
        if (optNote.isPresent()) {
            return optNote.get();
        }

        return null;
    }

    @Override
    public Task add(Task task) {
        return save(task);
    }

    @Override
    public Task save(Task model) {

        return taskRepository.save(model);
    }

    @Override
    public Task update(TaskDto dto, Task model) {

        model.setContent(dto.getContent());
        model.setStatus(TaskStatus.valueOf(dto.getStatus()));

        return save(model);
    }
}
