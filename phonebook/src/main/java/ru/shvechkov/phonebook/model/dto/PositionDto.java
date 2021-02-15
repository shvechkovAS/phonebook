package ru.shvechkov.phonebook.model.dto;

import lombok.Data;
import ru.shvechkov.phonebook.model.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class PositionDto {
    private Long id;
    private String namePosition;
    private Double salary;
    private int countEmployee;
    private List<PlainEmployeeDto> plainEmployeesDto = new ArrayList<>();

    public static PositionDto fromEntity(Position position){
        PositionDto positionDto = new PositionDto();
        positionDto.setId(position.getId());
        positionDto.setNamePosition(position.getNamePosition());
        positionDto.setSalary(position.getSalary());
        positionDto.setPlainEmployeesDto(position.getEmployees().stream().map(PlainEmployeeDto::fromEntity).collect(Collectors.toList()));
        positionDto.setCountEmployee(position.getEmployees().size());
        return positionDto;
    }
}
