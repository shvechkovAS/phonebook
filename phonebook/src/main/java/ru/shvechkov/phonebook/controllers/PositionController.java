package ru.shvechkov.phonebook.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.shvechkov.phonebook.model.Position;
import ru.shvechkov.phonebook.model.dto.DepartmentDto;
import ru.shvechkov.phonebook.model.dto.EmployeeDto;
import ru.shvechkov.phonebook.model.dto.PositionDto;
import ru.shvechkov.phonebook.service.PositionService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/position")
public class PositionController {

    public final PositionService positionService;

    public PositionController(PositionService positionService) {
        this.positionService = positionService;
    }

    @PostMapping
    public ResponseEntity<PositionDto> addPosition(@RequestBody final PositionDto positionDto){
        Position position = positionService.addPosition(Position.fromDto(positionDto));
        return new ResponseEntity<>(PositionDto.fromEntity(position), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PositionDto>> getPositions(){
        List<Position> positions = positionService.getPositions();
        List<PositionDto> positionsDto = positions.stream().map(PositionDto::fromEntity).collect(Collectors.toList());
        return new ResponseEntity<>(positionsDto, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<PositionDto> getPosition(@PathVariable final Long id){
        Position position = positionService.getPosition(id);
        return new ResponseEntity<>(PositionDto.fromEntity(position), HttpStatus.OK);
    }

    @GetMapping(value = "{name}/getByName")
    public ResponseEntity<List<PositionDto>> getPositionsByName(@PathVariable final String name){
        List<Position> positions = positionService.getPositionsByName(name);
        List<PositionDto> positionsDto = positions.stream().map(PositionDto::fromEntity).collect(Collectors.toList());
        return new ResponseEntity<>(positionsDto, HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<PositionDto> removePosition(@PathVariable final Long id){
        Position position = positionService.removePosition(id);
        return new ResponseEntity<>(PositionDto.fromEntity(position), HttpStatus.OK);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<PositionDto> updatePosition(@PathVariable final Long id,
                                                      @RequestBody final PositionDto positionDto){
        Position updatedPosition = positionService.updatePosition(id, Position.fromDto(positionDto));
        return new ResponseEntity<>(PositionDto.fromEntity(updatedPosition), HttpStatus.OK);
    }

    @PostMapping(value = "{positionId}/employees/{employeeId}/add")
    public ResponseEntity<PositionDto> addPositionToEmployee(@PathVariable final Long positionId,
                                                             @PathVariable final Long employeeId,
                                                             @RequestBody final EmployeeDto employeeDto){
        Position position = positionService.addEmployeeToPosition(positionId, employeeId);
        return new ResponseEntity<>(PositionDto.fromEntity(position), HttpStatus.OK);
    }

    @DeleteMapping(value = "{positionId}/employees/{employeeId}/remove")
    public ResponseEntity<PositionDto> removePositionFromEmployee(@PathVariable final Long positionId,
                                                                  @PathVariable final Long employeeId
                                                                  ){
        Position position = positionService.removeEmployeeFromPosition(positionId, employeeId);
        return new ResponseEntity<>(PositionDto.fromEntity(position), HttpStatus.OK);
    }
}
