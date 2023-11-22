package com.farjuce.appreservas.logica;

import com.farjuce.appreservas.bd.task.Task;
import com.farjuce.appreservas.bd.task.TaskRepository;
import com.farjuce.appreservas.controller.dto.TaskDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskLogic {

    private TaskRepository taskRepository;

    public TaskLogic(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> addTask(List<TaskDTO> taskDTOs) {
        List<Task> tasks = new ArrayList<>();
        for (TaskDTO taskDTO : taskDTOs) {
            Task task = new Task();
            task.setName(taskDTO.getName());
            task.setDescription(taskDTO.getDescription());
            task.setDuration(taskDTO.getDuration());
            task.setPrice(taskDTO.getPrice());
            tasks.add(taskRepository.save(task));
        }
        return tasks;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
}
