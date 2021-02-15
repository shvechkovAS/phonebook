package ru.shvechkov.phonebook.model;

import lombok.Data;
import ru.shvechkov.phonebook.model.dto.EmployeeDto;
import ru.shvechkov.phonebook.model.dto.PositionDto;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Entity
@Table(name = "Employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String secondName;
    private String firstName;
    private String surname;
    private String phoneNumber;
    private Date birthDate;
    private String email;
    private Gender gender;

    @ManyToOne(fetch = FetchType.LAZY)
    private Department department;

    @ManyToOne(fetch = FetchType.LAZY)
    private Organization organization;

    @ManyToMany
    @JoinTable(
            name = "positions_employees",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "position_id"))
    private List<Position> positions = new ArrayList<>();

    public Employee() {
    }

    public void addPositions(Position position){
        positions.add(position);
    }

    public void removePositions(Position position){
        positions.remove(position);
    }

    public static Employee fromDto(EmployeeDto employeeDto){
        Employee employee = new Employee();
        employee.setSecondName(employeeDto.getSecondName());
        employee.setFirstName(employeeDto.getFirstName());
        employee.setSurname(employeeDto.getSurname());
        employee.setPhoneNumber(employeeDto.getPhoneNumber());
        employee.setBirthDate(employeeDto.getBirthDate());
        employee.setEmail(employeeDto.getEmail());
        employee.setGender(employeeDto.getGender());

        return employee;
    }
}
