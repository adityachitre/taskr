package com.adichi.taskr.services.impl;

import com.adichi.taskr.dtos.TaskDto;
import com.adichi.taskr.entities.task.Task;
import com.adichi.taskr.entities.task.TaskStatus;
import com.adichi.taskr.repositories.TaskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static com.adichi.taskr.utils.SetupUtils.*;
import static java.util.Arrays.asList;
import static java.util.Optional.of;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TaskServiceImplTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskServiceImpl service;

    @Test
    public void getAllTasks() {

        Task t1 = createTask(ID1);
        Task t2 = createTask(ID2);

        doReturn(asList(t1, t2)).when(taskRepository).findAll();

        List<Task> tasks = service.getAllTasks();

        assertThat(tasks, is(hasSize(2)));
        verify(taskRepository, times(OCCURS_ONCE)).findAll();
        assertThat(tasks.get(0).getId(), is(ID1));
        assertThat(tasks.get(1).getId(), is(ID2));
    }

    @Test
    public void getTaskById() {

        doReturn(of(createTask(ID1))).when(taskRepository).findById(eq(ID1));

        Task task = service.getTaskById(ID1);

        assertThat(task.getId(), is(ID1));
    }

    @Test
    public void save() {

        service.save(createTask(ID1));

        verify(taskRepository, times(OCCURS_ONCE)).save(any(Task.class));
    }

    @Test
    public void update() {

        Task task = createTask(ID1);
        TaskDto taskDto = createTaskDto(ID1);

        Task savedTask = createTask(ID1);
        savedTask.setContent(taskDto.getContent());
        savedTask.setStatus(TaskStatus.valueOf(taskDto.getStatus()));
        doReturn(savedTask).when(taskRepository).save(any(Task.class));

        Task updatedTask = service.update(taskDto, task);

        assertThat(updatedTask.getContent(), is(DTO_CONTENT));
        assertThat(updatedTask.getStatus(), is(TaskStatus.valueOf(taskDto.getStatus())));
        verify(taskRepository, times(OCCURS_ONCE)).save(any(Task.class));
    }
}
