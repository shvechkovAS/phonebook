package ru.shvechkov.phonebook.model;

import lombok.Data;
import ru.shvechkov.phonebook.model.dto.DepartmentDto;
import ru.shvechkov.phonebook.model.dto.OrganizationDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Entity
@Table(name = "Organization")
public class Organization {

    @Id
    @GeneratedValue
    private Long id;
    private String nameOrg;
    private String directorName;
    private String inn;
    private String kpp;
    private String address;

    @OneToMany
    @JoinColumn(name = "organization_id")
    private List<Employee> employees = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "organization_id")
    private List<FilialBranch> filialBranches = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "organization_id")
    private List<Department> departments = new ArrayList<>();

    public Organization() {
    }

    public static Organization fromDto(OrganizationDto organizationDto){
        Organization organization = new Organization();
        organization.setNameOrg(organizationDto.getNameOrg());
        organization.setDirectorName(organizationDto.getDirectorName());
        organization.setInn(organizationDto.getInn());
        organization.setKpp(organizationDto.getKpp());
        organization.setAddress(organizationDto.getAddress());
        organization.setEmployees(organizationDto.getEmployeesDto().stream().map(Employee::fromDto).collect(Collectors.toList()));
        organization.setFilialBranches(organizationDto.getFilialBranchesDto().stream().map(FilialBranch::fromDto).collect(Collectors.toList()));
        organization.setDepartments(organizationDto.getDepartmentsDto().stream().map(Department::fromDto).collect(Collectors.toList()));
        return organization;
    }

    public void addEmployee(Employee employee){
        employees.add(employee);
    }

    public void removeEmployee(Employee employee){
        employees.remove(employee);
    }

    public void addDepartment(Department department){
        departments.add(department);
    }

    public void removeDepartment(Department department){ departments.remove(department); }

    public void addFilialBranch(FilialBranch filialBranch){
        filialBranches.add(filialBranch);
    }

    public void removeFilialBranch(FilialBranch filialBranch){
        filialBranches.remove(filialBranch);
    }
}