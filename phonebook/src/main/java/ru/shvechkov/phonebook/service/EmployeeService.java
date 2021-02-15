package ru.shvechkov.phonebook.service;

import org.springframework.stereotype.Service;
import ru.shvechkov.phonebook.model.Employee;
import ru.shvechkov.phonebook.model.Position;
import ru.shvechkov.phonebook.repository.EmployeeRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service

public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee addEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    public List<Employee> getEmployees(){
        return new ArrayList<>(employeeRepository.findAll());
    }

    public Employee getEmployee(Long id){
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Не удалось найти работника с id = " + id));
    }

    public Employee removeEmployee (Long id){
        Employee employee = getEmployee(id);
        employeeRepository.delete(employee);
        return employee;
    }

    public List<Employee> getEmployeesBySecondName(String secondName){
        return new ArrayList<>(employeeRepository.findEmployeesBySecondNameContains(secondName));
    }

    @Transactional
    public Employee updateEmployee (Long id, Employee employee){
        Employee employeeToEdit = getEmployee(id);
        employeeToEdit.setFirstName(employee.getFirstName());
        employeeToEdit.setSecondName(employee.getSecondName());
        employeeToEdit.setSurname(employee.getSurname());
        employeeToEdit.setGender(employee.getGender());
        employeeToEdit.setBirthDate(employee.getBirthDate());
        employeeToEdit.setPhoneNumber(employee.getPhoneNumber());
        employeeToEdit.setEmail(employee.getEmail());
        employeeToEdit.setOrganization(employee.getOrganization());
        return employeeToEdit;
    }

}