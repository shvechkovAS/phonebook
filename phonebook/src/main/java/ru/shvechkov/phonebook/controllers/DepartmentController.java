package ru.shvechkov.phonebook.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.shvechkov.phonebook.model.Department;
import ru.shvechkov.phonebook.model.Organization;
import ru.shvechkov.phonebook.model.dto.DepartmentDto;
import ru.shvechkov.phonebook.model.dto.EmployeeDto;
import ru.shvechkov.phonebook.model.dto.OrganizationDto;
import ru.shvechkov.phonebook.service.DepartmentService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    public ResponseEntity<DepartmentDto> addDepartment(@RequestBody final DepartmentDto departmentDto){
        Department department = departmentService.addDepartment(Department.fromDto(departmentDto));
        return new ResponseEntity<>(DepartmentDto.fromEntity(department), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDto>> getDepartments(){
        List<Department> departments = departmentService.getDepartments();
        List<DepartmentDto> departmentsDto = departments.stream().map(DepartmentDto::fromEntity).collect(Collectors.toList());
        return new ResponseEntity<>(departmentsDto, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<DepartmentDto> getDepartment(@PathVariable final Long id){
        Department department = departmentService.getDepartment(id);
        return new ResponseEntity<>(DepartmentDto.fromEntity(department), HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<DepartmentDto> deleteDepartment(@PathVariable final Long id){
        Department department = departmentService.removeDepartment(id);
        return new ResponseEntity<>(DepartmentDto.fromEntity(department), HttpStatus.OK);
    }

    @GetMapping(value = "depName/{depName}")
    public ResponseEntity<List<DepartmentDto>> getDepartmentByDepName(@PathVariable final String depName){
        List<Department> departments = departmentService.getDepartmentByNameDep(depName);
        List<DepartmentDto> departmentsDto = departments.stream().map(DepartmentDto::fromEntity).collect(Collectors.toList());
        return new ResponseEntity<>(departmentsDto, HttpStatus.OK);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<DepartmentDto> updateDepartment(@PathVariable final Long id,
                                                      @RequestBody final DepartmentDto departmentDto){
        Department updatedDepartment = departmentService.updateDepartment(id, Department.fromDto(departmentDto));
        return new ResponseEntity<>(DepartmentDto.fromEntity(updatedDepartment), HttpStatus.OK);
    }

    @PostMapping(value = "{departmentId}/employee/{employeeId}/add")
    public ResponseEntity<DepartmentDto> addEmployeeToDepartment(@PathVariable final Long departmentId,
                                                                 @PathVariable final Long employeeId,
                                                                 @RequestBody final EmployeeDto employeeDto){
        Department department = departmentService.addEmployeeToDepartment(employeeId, departmentId);
        return new ResponseEntity<>(DepartmentDto.fromEntity(department), HttpStatus.OK);
    }

    @DeleteMapping(value = "{departmentId}/employee/{employeeId}/remove")
    public ResponseEntity<DepartmentDto> removeEmployeeFromDepartment(@PathVariable final Long departmentId,
                                                                 @PathVariable final Long employeeId){
        Department department = departmentService.removeEmployeeFromDepartment(employeeId, departmentId);
        return new ResponseEntity<>(DepartmentDto.fromEntity(department), HttpStatus.OK);
    }
}