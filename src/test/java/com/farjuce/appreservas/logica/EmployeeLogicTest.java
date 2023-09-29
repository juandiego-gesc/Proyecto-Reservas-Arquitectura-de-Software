package com.farjuce.appreservas.logica;

import com.farjuce.appreservas.bd.employee.Employee;
import com.farjuce.appreservas.bd.employee.EmployeeRepository;
import com.farjuce.appreservas.bd.task.Task;
import com.farjuce.appreservas.bd.task.TaskRepository;
import com.farjuce.appreservas.controller.dto.EmployeeDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@ActiveProfiles(profiles = "test")
@ExtendWith(MockitoExtension.class)
class EmployeeLogicTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    EmployeeLogic employeeLogic;

    @InjectMocks
    TaskLogic taskLogic;

    @Test
    void Given_employee_with_task_When_added_Then_save_employee() {
        Task task = new Task();
        task.setTask_id(1L);

        Mockito.when(taskRepository.getReferenceById(task.getTask_id())).thenReturn(task);
        EmployeeDTO employeeDTO = new EmployeeDTO("Employee Name", task.getTask_id());
        employeeLogic.addEmployee(employeeDTO);
        Employee employee = new Employee();
        employee.setName(employeeDTO.getName());
        employee.setTask(taskRepository.getReferenceById(task.getTask_id()));

        Mockito.verify(employeeRepository).save(employee);
    }

    @Test
    void Given_employees_When_get_all_employees_Then_return_all_employees() {
        List<Employee> employees = new ArrayList<>();
        List<EmployeeDTO> employeesDTO = new ArrayList<>();
        Task task = new Task();
        task.setTask_id(1L);

        Mockito.when(taskRepository.getReferenceById(task.getTask_id())).thenReturn(task);
        EmployeeDTO employeeDTO1 = new EmployeeDTO("Employee Name 1", task.getTask_id());
        EmployeeDTO employeeDTO2 = new EmployeeDTO("Employee Name 2", task.getTask_id());

        employeesDTO.add(employeeDTO1);
        employeesDTO.add(employeeDTO2);

        for (EmployeeDTO employeeDTO : employeesDTO) {
            employeeLogic.addEmployee(employeeDTO);
            Employee employee = new Employee();
            employee.setName(employeeDTO.getName());
            employee.setTask(taskRepository.getReferenceById(task.getTask_id()));
            employees.add(employee);
        }

        Mockito.when(employeeRepository.findAll()).thenReturn(employees);

        List<Employee> employeesReturned = employeeLogic.getAllEmployees();
        Mockito.verify(employeeRepository).findAll();
        assertEquals("Lists size Test", employees, employeesReturned);
    }
}