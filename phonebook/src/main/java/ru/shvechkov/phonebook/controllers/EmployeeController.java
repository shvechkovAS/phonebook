package ru.shvechkov.phonebook.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.shvechkov.phonebook.model.Employee;
import ru.shvechkov.phonebook.model.dto.EmployeeDto;
import ru.shvechkov.phonebook.service.EmployeeService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> addEmployee(@RequestBody final EmployeeDto employeeDto){
        Employee employee = employeeService.addEmployee(Employee.fromDto(employeeDto));
        return new ResponseEntity<>(EmployeeDto.fromEntity(employee), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getEmployees(){
        List<Employee> employees = employeeService.getEmployees();
        List<EmployeeDto> employeesDto = employees.stream().map(EmployeeDto::fromEntity).collect(Collectors.toList());
        return new ResponseEntity<>(employeesDto, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<EmployeeDto> getEmployee(@PathVariable final Long id){
        Employee employee = employeeService.getEmployee(id);
        return new ResponseEntity<>(EmployeeDto.fromEntity(employee), HttpStatus.OK);
    }

    @GetMapping(value = "secondName/{secondName}")
    public ResponseEntity<List<EmployeeDto>> getEmployeeBySecondName(@PathVariable final String secondName){
        List<Employee> employees = employeeService.getEmployeesBySecondName(secondName);
        List<EmployeeDto> employeesDto = employees.stream().map(EmployeeDto::fromEntity).collect(Collectors.toList());
        return new ResponseEntity<>(employeesDto, HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<EmployeeDto> deleteEmployee(@PathVariable final Long id){
        Employee employee = employeeService.removeEmployee(id);
        return new ResponseEntity<>(EmployeeDto.fromEntity(employee), HttpStatus.OK);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable final Long id,
                                                      @RequestBody final EmployeeDto employeeDto){
        Employee updatedEmployee = employeeService.updateEmployee(id, Employee.fromDto(employeeDto));
        return new ResponseEntity<>(EmployeeDto.fromEntity(updatedEmployee), HttpStatus.OK);
        }

}
