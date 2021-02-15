package ru.shvechkov.phonebook.model.dto;

import lombok.Data;
import ru.shvechkov.phonebook.model.Department;

import java.util.Objects;
import java.util.stream.Collectors;

@Data
public class PlainDepartmentDto {
    private Long id;
    private String depName;

    public static PlainDepartmentDto fromEntity(Department department){
        PlainDepartmentDto plainDepartmentDto = new PlainDepartmentDto();
        plainDepartmentDto.setId(department.getId());
        plainDepartmentDto.setDepName(department.getDepName());
        return plainDepartmentDto;
    }

}
