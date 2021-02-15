package ru.shvechkov.phonebook.model.dto;

import lombok.Data;
import ru.shvechkov.phonebook.model.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class PlainPositionDto {
    private Long id;
    private String namePosition;
    private Double salary;
    private int countEmployee;

    public static PlainPositionDto fromEntity(Position position){
        PlainPositionDto plainPositionDto = new PlainPositionDto();
        plainPositionDto.setId(position.getId());
        plainPositionDto.setNamePosition(position.getNamePosition());
        plainPositionDto.setSalary(position.getSalary());
        plainPositionDto.setCountEmployee(position.getEmployees().size());
        return plainPositionDto;
    }
}
