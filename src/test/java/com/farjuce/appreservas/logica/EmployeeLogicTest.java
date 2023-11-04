package com.farjuce.appreservas.logica;

import com.farjuce.appreservas.bd.brach.Branch;
import com.farjuce.appreservas.bd.brach.BranchRepository;
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

    @Mock
    private BranchRepository branchRepository;

    @InjectMocks
    EmployeeLogic employeeLogic;

    @InjectMocks
    TaskLogic taskLogic;

    @Test
    void Given_employee_with_task_When_added_Then_save_employee() {
        Task task = new Task();
        task.setTaskId(1L);
        Branch branch = new Branch();
        branch.setBranchId(1L);

        Mockito.when(taskRepository.getReferenceById(task.getTaskId())).thenReturn(task);
        Mockito.when(branchRepository.getReferenceById(branch.getBranchId())).thenReturn(branch);
        EmployeeDTO employeeDTO = new EmployeeDTO("Employee Name", task.getTaskId(),branch.getBranchId());
        employeeLogic.addEmployee(employeeDTO);
        Employee employee = new Employee();
        employee.setName(employeeDTO.getName());
        employee.setTask(taskRepository.getReferenceById(task.getTaskId()));
        employee.setBranch(branchRepository.getReferenceById(branch.getBranchId()));

        Mockito.verify(employeeRepository).save(employee);
    }

    @Test
    void Given_employees_When_get_all_employees_Then_return_all_employees() {
        List<Employee> employees = new ArrayList<>();
        List<EmployeeDTO> employeesDTO = new ArrayList<>();
        Task task = new Task();
        task.setTaskId(1L);
        Branch branch = new Branch();
        branch.setBranchId(1L);

        Mockito.when(taskRepository.getReferenceById(task.getTaskId())).thenReturn(task);
        Mockito.when(branchRepository.getReferenceById(branch.getBranchId())).thenReturn(branch);
        EmployeeDTO employeeDTO1 = new EmployeeDTO("Employee Name 1", task.getTaskId(),branch.getBranchId());
        EmployeeDTO employeeDTO2 = new EmployeeDTO("Employee Name 2", task.getTaskId(),branch.getBranchId());

        employeesDTO.add(employeeDTO1);
        employeesDTO.add(employeeDTO2);

        for (EmployeeDTO employeeDTO : employeesDTO) {
            employeeLogic.addEmployee(employeeDTO);
            Employee employee = new Employee();
            employee.setName(employeeDTO.getName());
            employee.setTask(taskRepository.getReferenceById(task.getTaskId()));
            employee.setBranch(branchRepository.getReferenceById(branch.getBranchId()));
            employees.add(employee);
        }

        Mockito.when(employeeRepository.findAll()).thenReturn(employees);

        List<Employee> employeesReturned = employeeLogic.getAllEmployees();
        Mockito.verify(employeeRepository).findAll();
        assertEquals("Lists size Test", employees, employeesReturned);
    }
}