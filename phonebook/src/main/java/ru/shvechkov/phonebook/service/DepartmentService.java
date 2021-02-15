package ru.shvechkov.phonebook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.shvechkov.phonebook.model.Department;
import ru.shvechkov.phonebook.model.Employee;
import ru.shvechkov.phonebook.model.Organization;
import ru.shvechkov.phonebook.repository.DepartmentRepository;

import javax.transaction.Transactional;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final EmployeeService employeeService;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository, EmployeeService employeeService) {
        this.departmentRepository = departmentRepository;
        this.employeeService = employeeService;
    }

    public Department addDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public List<Department> getDepartments(){
        return StreamSupport
                .stream(departmentRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Department getDepartment(Long id){
        return departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Не удалось найти отдел с id = " + id));
    }

    public Department removeDepartment (Long id){
        Department department = getDepartment(id);
        departmentRepository.delete(department);
        return department;
    }

    public List<Department> getDepartmentByNameDep(String nameDep){
        return departmentRepository.findDepartmentByDepNameContaining(nameDep);
    }

    @Transactional
    public Department updateDepartment(Long id, Department department){
        Department departmentToEdit = getDepartment(id);
        departmentToEdit.setDepName(department.getDepName());
        departmentToEdit.setEmployees(department.getEmployees());
        return departmentToEdit;
    }

    @Transactional
    public Department addEmployeeToDepartment(Long employeeId, Long departmentId){
        Department department = getDepartment(departmentId);
        Employee employee = employeeService.getEmployee(employeeId);
        if (Objects.nonNull(employee.getDepartment())) {
            throw new
                    RuntimeException(MessageFormat.format("Сотрудник {0} уже принадлежит отделу {1}",employeeId,
                    employee.getDepartment().getId()));
        }
        department.addEmployee(employee);
        employee.setDepartment(department);
        return department;
    }

    @Transactional
    public Department removeEmployeeFromDepartment(Long employeeId, Long departmentId){
        Employee employee = employeeService.getEmployee(employeeId);
        Department department = getDepartment(departmentId);
        department.removeEmployee(employee);
        return department;
    }
}
