package ru.shvechkov.phonebook.model.dto;

import lombok.Data;
import ru.shvechkov.phonebook.model.Employee;
import ru.shvechkov.phonebook.model.Gender;

import java.sql.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
public class EmployeeDto {
    private Long id;
    private String secondName;
    private String firstName;
    private String surname;
    private String phoneNumber;
    private Date birthDate;
    private String email;
    private String genderS;
    private Gender gender;
    private List<PlainPositionDto> plainPositionsDto;
    private PlainDepartmentDto plainDepartmentDto;
    private PlainOrgDto plainOrgDto;

    public static EmployeeDto fromEntity (Employee employee){
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employee.getId());
        employeeDto.setSecondName(employee.getSecondName());
        employeeDto.setFirstName(employee.getFirstName());
        employeeDto.setSurname(employee.getSurname());
        employeeDto.setPhoneNumber(employee.getPhoneNumber());
        employeeDto.setBirthDate(employee.getBirthDate());
        employeeDto.setEmail(employee.getEmail());
        employeeDto.setGender(employee.getGender());
        if (employee.getGender().equals(Gender.MALE)){
            employeeDto.setGenderS("М");
        }
        if (employee.getGender().equals(Gender.FEMALE)){
            employeeDto.setGenderS("Ж");
        }
        employeeDto.setPlainPositionsDto(employee.getPositions().stream().map(PlainPositionDto::fromEntity).collect(Collectors.toList()));
        if(Objects.nonNull(employee.getDepartment())){
            employeeDto.setPlainDepartmentDto(PlainDepartmentDto.fromEntity(employee.getDepartment()));
        }
        if(Objects.nonNull(employee.getOrganization())){
            employeeDto.setPlainOrgDto(PlainOrgDto.fromEntity(employee.getOrganization()));
        }
        return employeeDto;
    }
}
