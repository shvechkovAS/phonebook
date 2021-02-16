
package ru.shvechkov.phonebook.service;

import org.springframework.stereotype.Service;
import ru.shvechkov.phonebook.model.Department;
import ru.shvechkov.phonebook.model.Employee;
import ru.shvechkov.phonebook.model.Position;
import ru.shvechkov.phonebook.repository.PositionRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PositionService {

    private final PositionRepository positionRepository;
    private final EmployeeService employeeService;


    public PositionService(PositionRepository positionRepository,
                           EmployeeService employeeService) {
        this.positionRepository = positionRepository;
        this.employeeService = employeeService;
    }

    public List<Position> getPositionsByName(String name){
        return positionRepository.findPositionsByNamePositionContaining(name);
    }

    public Position addPosition(Position position){
        return positionRepository.save(position);
    }

    public List<Position> getPositions(){
        return StreamSupport
                .stream(positionRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Position getPosition(Long id){
        return positionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Не удалось найти должность с id = " + id));
    }

    public Position removePosition(Long id){
        Position position = getPosition(id);
        positionRepository.delete(position);
        return position;
    }

    @Transactional
    public Position updatePosition(Long id, Position position){
        Position positionToEdit = getPosition(id);
        positionToEdit.setNamePosition(position.getNamePosition());
        positionToEdit.setSalary(position.getSalary());
        positionToEdit.setCountEmployees(position.getCountEmployees());
        return positionToEdit;
    }

    @Transactional
    public Position addEmployeeToPosition(Long positionId, Long employeeId){
        Position position = getPosition(positionId);
        Employee employee = employeeService.getEmployee(employeeId);
        List<Employee> emps = position.getEmployees();
        if(!emps.contains(employee)){
        position.addEmployee(employee);
        employee.addPositions(position);
        }
        return position;
    }

    @Transactional
    public Position removeEmployeeFromPosition(Long positionId, Long employeeId){
        Position position = getPosition(positionId);
        Employee employee = employeeService.getEmployee(employeeId);
        position.removeEmployee(employee);
        employee.removePositions(position);
        return position;
    }

}
