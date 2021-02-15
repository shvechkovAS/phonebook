package ru.shvechkov.phonebook.model.dto;

import lombok.Data;
import ru.shvechkov.phonebook.model.Department;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
public class DepartmentDto {
    private Long id;
    private String depName;
    private List<EmployeeDto> employeesDto = new ArrayList<>();
    private PlainFilialBranchDto plainFilialBranchDto;
    private PlainOrgDto plainOrgDto;

    public static DepartmentDto fromEntity(Department department){
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setId(department.getId());
        departmentDto.setDepName(department.getDepName());
        departmentDto.setEmployeesDto(department.getEmployees().stream().map(EmployeeDto::fromEntity).collect(Collectors.toList()));
        if(Objects.nonNull(department.getFilialBranch())){
            departmentDto.setPlainFilialBranchDto(PlainFilialBranchDto.fromEntity(department.getFilialBranch()));
        }
        if(Objects.nonNull(department.getOrganization())){
            departmentDto.setPlainOrgDto(PlainOrgDto.fromEntity(department.getOrganization()));
        }
        return departmentDto;
    }

}
