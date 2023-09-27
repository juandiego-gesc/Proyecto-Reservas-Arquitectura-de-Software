package com.farjuce.appreservas.logica;

import com.farjuce.appreservas.bd.employee.Employee;
import com.farjuce.appreservas.bd.employee.EmployeeRepository;
import com.farjuce.appreservas.bd.task.Task;
import com.farjuce.appreservas.bd.task.TaskRepository;
import com.farjuce.appreservas.controller.dto.EmployeeDTO;
import com.farjuce.appreservas.controller.dto.TaskDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(profiles = "test")
@ExtendWith(MockitoExtension.class)
class EmployeeLogicTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    EmployeeLogic employeeLogic;

    @Test
    void Given_employee_with_task_When_added_Then_save_employee(){
        Task task = new Task();
        task.setTask_id(1L);
        task.setName("Test Task 1");
        task.setDescription("Test Description 1");
        task.setDuration(20);
        task.setPrice(50);

        //taskRepository.save(task);

        EmployeeDTO employeeDTO = new EmployeeDTO("Employee Name", task.getTask_id());
        employeeLogic.addEmployee(employeeDTO);
        Employee employee = new Employee();
        employee.setName(employeeDTO.getName());
        employee.setTask(taskRepository.getReferenceById(task.getTask_id()));

        Mockito.verify(employeeRepository).save(employee);
    }


}