package ru.shvechkov.phonebook.service;

import org.springframework.stereotype.Service;
import ru.shvechkov.phonebook.model.Department;
import ru.shvechkov.phonebook.model.Employee;
import ru.shvechkov.phonebook.model.FilialBranch;
import ru.shvechkov.phonebook.model.Organization;
import ru.shvechkov.phonebook.repository.OrganizationRepository;

import javax.transaction.Transactional;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class OrganizationService {

    private final OrganizationRepository organizationRepository;
    private final FilialBranchService filialBranchService;
    private final EmployeeService employeeService;
    private final DepartmentService departmentService;

    public OrganizationService(OrganizationRepository organizationRepository,
                              FilialBranchService filialBranchService,
                              EmployeeService employeeService,
                              DepartmentService departmentService) {
        this.organizationRepository = organizationRepository;
        this.filialBranchService = filialBranchService;
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }

    public Organization addOrganization(Organization organization){
        return organizationRepository.save(organization);
    }

    public List<Organization> getOrganizations(){
        return new ArrayList<>(organizationRepository.findAll());
    }

    public Organization getOrganization(Long id){
        return organizationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Не удалось найти организацию с id = " + id));
    }

    public List<Organization> findOrganizationsByNameOrg(String nameOrg){
        return organizationRepository.findOrganizationByNameOrgContaining(nameOrg);
    }

    public List<Organization> findOrganizationsByInn (String inn){
        return organizationRepository.findOrganizationByInnContaining(inn);
    }

    public Organization removeOrganization (Long id){
        Organization organization = getOrganization(id);
        organizationRepository.delete(organization);
        return organization;
    }

    @Transactional
    public Organization updateOrganization(Long id, Organization organization){
        Organization organizationToUpdate = getOrganization(id);
        organizationToUpdate.setNameOrg(organization.getNameOrg());
        organizationToUpdate.setDirectorName(organization.getDirectorName());
        organizationToUpdate.setInn(organization.getInn());
        organizationToUpdate.setKpp(organization.getKpp());
        organizationToUpdate.setAddress(organization.getAddress());
        organizationToUpdate.setEmployees(organization.getEmployees());
        organizationToUpdate.setDepartments(organization.getDepartments());
        organizationToUpdate.setFilialBranches(organization.getFilialBranches());
        return organizationToUpdate;
    }

    @Transactional
    public Organization addFilialBranchToOrganization(Long filialBranchId, Long organizationId){
        Organization organization = getOrganization(organizationId);
        FilialBranch filialBranch = filialBranchService.getFilialBranch(filialBranchId);
        if (Objects.nonNull(filialBranch.getOrganization())) {
            throw new
                    RuntimeException(MessageFormat.format("Филиал {0} уже принадлежит организации {1}", filialBranchId,
                    filialBranch.getOrganization().getId()));
        }
        organization.addFilialBranch(filialBranch);
        filialBranch.setOrganization(organization);
        return organization;
    }

    @Transactional
    public Organization removeFilialBranchFromOrganization(Long filialBranchId, Long organizationId){
        Organization organization = getOrganization(organizationId);
        FilialBranch filialBranch = filialBranchService.getFilialBranch(filialBranchId);
        organization.removeFilialBranch(filialBranch);
        return organization;
    }

    @Transactional
    public Organization addDepartmentToOrganization(Long departmentId, Long organizationId){
        Organization organization = getOrganization(organizationId);
        Department department = departmentService.getDepartment(departmentId);
        if (Objects.nonNull(department.getOrganization())) {
            throw new
                    RuntimeException(MessageFormat.format("Отдел {0} уже принадлежит организации {1}", departmentId,
                    department.getOrganization().getId()));
        }
        organization.addDepartment(department);
        department.setOrganization(organization);
        return organization;
    }

    @Transactional
    public Organization removeDepartmentFromOrganization(Long departmentId, Long organizationId){
        Organization organization = getOrganization(organizationId);
        Department department = departmentService.getDepartment(departmentId);
        organization.removeDepartment(department);
        return organization;
    }

    @Transactional
    public Organization addEmployeeToOrganization(Long employeeId, Long organizationId){
        Organization organization = getOrganization(organizationId);
        Employee employee = employeeService.getEmployee(employeeId);
        if (Objects.nonNull(employee.getOrganization())) {
            throw new
                    RuntimeException(MessageFormat.format("Отдел {0} уже принадлежит организации {1}", employeeId,
                    employee.getOrganization().getId()));
        }
        organization.addEmployee(employee);
        employee.setOrganization(organization);
        return organization;
    }

    @Transactional
    public Organization removeEmployeeFromOrganization(Long employeeId, Long organizationId){
        Organization organization = getOrganization(organizationId);
        Employee employee = employeeService.getEmployee(employeeId);
        organization.removeEmployee(employee);
        return organization;
    }
}
