package ru.shvechkov.phonebook.model.dto;

import lombok.Data;
import ru.shvechkov.phonebook.model.Department;
import ru.shvechkov.phonebook.model.Employee;
import ru.shvechkov.phonebook.model.FilialBranch;
import ru.shvechkov.phonebook.model.Organization;

import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class OrganizationDto {
    private Long id;
    private String nameOrg;
    private String directorName;
    private String inn;
    private String kpp;
    private String address;
    private List<EmployeeDto> employeesDto = new ArrayList<>();
    private List<FilialBranchDto> filialBranchesDto = new ArrayList<>();
    private List<DepartmentDto> departmentsDto = new ArrayList<>();

    public static OrganizationDto fromEntity(Organization organization){
        OrganizationDto organizationDto = new OrganizationDto();
        organizationDto.setId(organization.getId());
        organizationDto.setNameOrg(organization.getNameOrg());
        organizationDto.setDirectorName(organization.getDirectorName());
        organizationDto.setInn(organization.getInn());
        organizationDto.setKpp(organization.getKpp());
        organizationDto.setAddress(organization.getAddress());
        organizationDto.setEmployeesDto(organization.getEmployees().stream().map(EmployeeDto::fromEntity).collect(Collectors.toList()));
        organizationDto.setFilialBranchesDto(organization.getFilialBranches().stream().map(FilialBranchDto::fromEntity).collect(Collectors.toList()));
        organizationDto.setDepartmentsDto(organization.getDepartments().stream().map(DepartmentDto::fromEntity).collect(Collectors.toList()));
        return organizationDto;
    }
}
