package com.farjuce.appreservas.bd.employee;

import com.farjuce.appreservas.bd.appointment.Appointment;
import com.farjuce.appreservas.bd.task.Task;
import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "employee")
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long employee_id;

    @Column
    private String name;

//    @ManyToMany (cascade = { CascadeType.PERSIST, CascadeType.MERGE })
//    @JoinTable(
//        name = "employee_task",
//        joinColumns = @JoinColumn(name = "employee_id"),
//        inverseJoinColumns = @JoinColumn(name = "task_id")
//    )
//    @JsonIgnoreProperties("employees") // Exclude the serialization of the 'employees' property
//    @JsonIgnore
//    private Set<Task> tasks;

    @ManyToOne
    @JoinColumn (name = "task_id")
    @JsonIgnoreProperties({"employees"})
    @JsonManagedReference
    private Task task;

//    @OneToMany (mappedBy= "employee")
//    private Set<Appointment> appointments;

}
