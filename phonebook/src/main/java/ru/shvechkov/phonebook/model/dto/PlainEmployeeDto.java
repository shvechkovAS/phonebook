package ru.shvechkov.phonebook.model.dto;

import lombok.Data;
import ru.shvechkov.phonebook.model.Employee;
import ru.shvechkov.phonebook.model.Gender;

import java.sql.Date;
import java.util.Objects;

@Data
public class PlainEmployeeDto {
    private Long id;
    private String fio;
    private String firstName;
    private String surname;
    private String phoneNumber;
    private Date birthDate;
    private String email;
    private Gender gender;
    private PlainDepartmentDto plainDepartmentDto;

    public static PlainEmployeeDto fromEntity (Employee employee){
        PlainEmployeeDto plainEmployeeDto = new PlainEmployeeDto();
        plainEmployeeDto.setId(employee.getId());
        plainEmployeeDto.setFio(employee.getSecondName() + " " + employee.getFirstName() + " " +employee.getSurname());
        plainEmployeeDto.setPhoneNumber(employee.getPhoneNumber());
        plainEmployeeDto.setBirthDate(employee.getBirthDate());
        plainEmployeeDto.setEmail(employee.getEmail());
        plainEmployeeDto.setGender(employee.getGender());
        return plainEmployeeDto;
    }
}
