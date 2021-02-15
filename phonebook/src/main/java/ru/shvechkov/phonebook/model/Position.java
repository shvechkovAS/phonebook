
package ru.shvechkov.phonebook.model;

import lombok.Data;
import ru.shvechkov.phonebook.model.dto.PlainEmployeeDto;
import ru.shvechkov.phonebook.model.dto.PositionDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Entity
@Table( name = "Position")
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String namePosition;
    private Double salary;
    private int countEmployees;

    @ManyToMany(
            mappedBy = "positions"
    )
    private List<Employee> employees = new ArrayList<>();

    public Position() {
    }

    public static Position fromDto(PositionDto positionDto){
        Position position = new Position();
        position.setNamePosition(positionDto.getNamePosition());
        position.setSalary(positionDto.getSalary());
        position.setCountEmployees(positionDto.getPlainEmployeesDto().size());
        return position;
    }

    public void addEmployee(Employee employee){
        employees.add(employee);
    }

    public void removeEmployee(Employee employee){
        employees.remove(employee);
    }
}