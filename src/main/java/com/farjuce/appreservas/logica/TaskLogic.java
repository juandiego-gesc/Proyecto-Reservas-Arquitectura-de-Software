package com.farjuce.appreservas.logica;

import com.farjuce.appreservas.bd.task.Task;
import com.farjuce.appreservas.bd.task.TaskRepository;
import com.farjuce.appreservas.controller.dto.TaskDTO;
import com.farjuce.appreservas.controller.dto.CustomerDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskLogic {

    private TaskRepository taskRepository;

    public TaskLogic(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void addTask(TaskDTO taskDTO){
        Task task = new Task();
        task.setTask_id(taskDTO.getTaskId());
        task.setName(taskDTO.getName());
        task.setDescription(taskDTO.getDescription());
        task.setDuration(taskDTO.getDuration());
        task.setPrice(taskDTO.getPrice());
    }

    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

}
