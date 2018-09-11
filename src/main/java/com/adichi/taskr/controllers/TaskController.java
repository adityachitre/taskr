package com.adichi.taskr.controllers;

import com.adichi.taskr.dtos.TaskDto;
import com.adichi.taskr.entities.task.Task;
import com.adichi.taskr.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.adichi.taskr.transformers.TaskTransformer.transformToDto;
import static com.adichi.taskr.transformers.TaskTransformer.transformToModel;
import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping(value = "/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    @ResponseBody
    public List<TaskDto> getTasks() {

        return taskService.getAllTasks().parallelStream()
                .map(note -> transformToDto(note))
                .collect(toList());
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<TaskDto> getTask(@PathVariable UUID taskId) {

        Task task = taskService.getTaskById(taskId);
        if (task == null) {
            return noContent().build();
        }

        return ok(transformToDto(task));
    }

    @PostMapping
    @ResponseBody
    @ResponseStatus(CREATED)
    public TaskDto addTask(@RequestBody TaskDto taskDto) {

        Task model = transformToModel(taskDto);

        return transformToDto(taskService.add(model));
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable UUID taskId, @RequestBody TaskDto taskDto) {

        Task model = taskService.getTaskById(taskId);
        if (model == null) {

            return notFound().build();
        }

        return ok(transformToDto(taskService.update(taskDto, model)));
    }
}
