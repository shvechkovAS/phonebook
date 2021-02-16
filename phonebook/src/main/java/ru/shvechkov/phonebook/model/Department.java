package ru.shvechkov.phonebook.model;

import lombok.Data;
import ru.shvechkov.phonebook.model.dto.DepartmentDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Entity
@Table(name = "Department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String depName;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "department_id")
    private List<Employee> employees = new ArrayList<>();

    @ManyToOne()
    private FilialBranch filialBranch;

    @ManyToOne
    private Organization organization;

    public Department() {
    }

    public static Department fromDto(DepartmentDto departmentDto){
        Department department = new Department();
        department.setDepName(departmentDto.getDepName());
        department.setEmployees(departmentDto.getEmployeesDto().stream().map(Employee::fromDto).collect(Collectors.toList()));
        return department;
    }

    public void addEmployee(Employee employee){
        employees.add(employee);
    }

    public void removeEmployee(Employee employee){
        employees.remove(employee);
    }
}
