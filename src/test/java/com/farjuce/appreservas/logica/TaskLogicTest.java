package com.farjuce.appreservas.logica;

import com.farjuce.appreservas.bd.task.Task;
import com.farjuce.appreservas.bd.task.TaskRepository;
import com.farjuce.appreservas.controller.dto.TaskDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@ActiveProfiles(profiles = "test")
@ExtendWith(MockitoExtension.class)
class TaskLogicTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    TaskLogic taskLogic;

    @Test
    void Given_tasks_When_added_Then_save_all() {
        List<TaskDTO> tasksDTO = new ArrayList<>();
        TaskDTO taskDTO1 = new TaskDTO("Test Task 1", "Test Description 1", 20, 50);
        TaskDTO taskDTO2 = new TaskDTO("Test Task 2", "Test Description 2", 40, 100);
        tasksDTO.add(taskDTO1);
        tasksDTO.add(taskDTO2);

        taskLogic.addTask(tasksDTO);

        for (TaskDTO taskDTO:tasksDTO){
            Task expectedTask= new Task();
            expectedTask.setName(taskDTO.getName());
            expectedTask.setDescription(taskDTO.getDescription());
            expectedTask.setDuration(taskDTO.getDuration());
            expectedTask.setPrice(taskDTO.getPrice());
            Mockito.verify(taskRepository).save(expectedTask);
        }
        Mockito.verify(taskRepository,Mockito.times(2)).save(any(Task.class));
    }

    @Test
    void Given_tasks_When_get_all_tasks_Then_return_all_tasks(){
        List<TaskDTO> tasksDTO = new ArrayList<>();
        List<Task> tasks = new ArrayList<>();

        TaskDTO taskDTO1 = new TaskDTO("Test Task 1", "Test Description 1", 20, 50);
        TaskDTO taskDTO2 = new TaskDTO("Test Task 2", "Test Description 2", 40, 100);
        tasksDTO.add(taskDTO1);
        tasksDTO.add(taskDTO2);
        taskLogic.addTask(tasksDTO);

        for (TaskDTO taskDTO: tasksDTO){
            Task task = new Task();
            task.setName(taskDTO.getName());
            task.setDescription(taskDTO.getDescription());
            task.setDuration(taskDTO.getDuration());
            task.setPrice(taskDTO.getPrice());
            tasks.add(task);
        }

        Mockito.when(taskRepository.findAll()).thenReturn(tasks);

        List<Task> tasksReturned = taskLogic.getAllTasks();
        Mockito.verify(taskRepository).findAll();
        assertEquals("Lists size Test",tasksDTO.size(),tasksReturned.size());
    }
}