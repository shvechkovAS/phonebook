package ru.shvechkov.phonebook.model.dto;

import lombok.Data;
import ru.shvechkov.phonebook.model.FilialBranch;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
public class FilialBranchDto {

    private Long id;
    private String filialName;
    private String address;
    private PlainOrgDto plainOrgDto;
    private List<DepartmentDto> departmentsDto = new ArrayList<>();

    public static FilialBranchDto fromEntity(FilialBranch filialBranch){
        FilialBranchDto filialBranchDto = new FilialBranchDto();
        filialBranchDto.setId(filialBranch.getId());
        filialBranchDto.setFilialName(filialBranch.getFilialName());
        filialBranchDto.setAddress(filialBranch.getAddress());
        if(Objects.nonNull(filialBranch.getOrganization())) {
            filialBranchDto.setPlainOrgDto(PlainOrgDto.fromEntity(filialBranch.getOrganization()));
        }
        filialBranchDto.setDepartmentsDto(filialBranch.getDepartments().stream().map(DepartmentDto::fromEntity).collect(Collectors.toList()));
        return filialBranchDto;
    }
}
