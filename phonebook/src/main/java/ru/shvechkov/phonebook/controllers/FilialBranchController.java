package ru.shvechkov.phonebook.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.shvechkov.phonebook.model.FilialBranch;
import ru.shvechkov.phonebook.model.dto.DepartmentDto;
import ru.shvechkov.phonebook.model.dto.EmployeeDto;
import ru.shvechkov.phonebook.model.dto.FilialBranchDto;
import ru.shvechkov.phonebook.service.FilialBranchService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/filialBranch")
public class FilialBranchController {

    public final FilialBranchService filialBranchService;

    @Autowired
    public FilialBranchController(FilialBranchService filialBranchService) {
        this.filialBranchService = filialBranchService;
    }

    @PostMapping
    public ResponseEntity<FilialBranchDto> addFilialBranch(@RequestBody final FilialBranchDto filialBranchDto){
        FilialBranch filialBranch = filialBranchService.addFilialBranch(FilialBranch.fromDto(filialBranchDto));
        return new ResponseEntity<>(filialBranchDto.fromEntity(filialBranch), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<FilialBranchDto>> getFilialBranches(){
        List<FilialBranch> filialBranches = filialBranchService.getFilialBranches();
        List<FilialBranchDto> filialBranchesDto = filialBranches.stream().map(FilialBranchDto::fromEntity).collect(Collectors.toList());
        return new ResponseEntity<>(filialBranchesDto, HttpStatus.OK);
    }

    @GetMapping(value = "/name/{nameFil}")
    public ResponseEntity<List<FilialBranchDto>> getFilialBranchesByName(@PathVariable final String nameFil){
        List<FilialBranch> filialBranches = filialBranchService.getFilialBranchesByName(nameFil);
        List<FilialBranchDto> filialBranchesDto = filialBranches.stream().map(FilialBranchDto::fromEntity).collect(Collectors.toList());
        return new ResponseEntity<>(filialBranchesDto, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<FilialBranchDto> getFilialBranch(@PathVariable final Long id){
        FilialBranch filialBranch = filialBranchService.getFilialBranch(id);
        return new ResponseEntity<>(FilialBranchDto.fromEntity(filialBranch), HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<FilialBranchDto> deleteFilialBranch(@PathVariable final Long id){
        FilialBranch filialBranch = filialBranchService.removeFilialBranch(id);
        return new ResponseEntity<>(FilialBranchDto.fromEntity(filialBranch), HttpStatus.OK);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<FilialBranchDto> updateFilialBranch(@PathVariable final Long id,
                                                          @RequestBody final FilialBranchDto filialBranchDto){
        FilialBranch updatedFilialBranch = filialBranchService.updateFilialBranch(id, FilialBranch.fromDto(filialBranchDto));
        return new ResponseEntity<>(FilialBranchDto.fromEntity(updatedFilialBranch), HttpStatus.OK);
    }

    @PostMapping(value = "{filialBranchId}/department/{departmentId}/add")
    public ResponseEntity<FilialBranchDto> addDepartmentToFilialBranch(@PathVariable final Long filialBranchId,
                                                                       @PathVariable final Long departmentId,
                                                                       @RequestBody final DepartmentDto departmentDto){
        FilialBranch filialBranch = filialBranchService.addDepartmentToFilialBranch(filialBranchId, departmentId);
        return new ResponseEntity<>(FilialBranchDto.fromEntity(filialBranch), HttpStatus.OK);
    }

    @DeleteMapping(value = "{filialBranchId}/department/{departmentId}/remove")
    public ResponseEntity<FilialBranchDto> removeDepartmentFromFilialBranch(@PathVariable final Long filialBranchId,
                                                                      @PathVariable final Long departmentId){
        FilialBranch filialBranch = filialBranchService.removeDepartmentFromFilialBranch(filialBranchId, departmentId);
        return new ResponseEntity<>(FilialBranchDto.fromEntity(filialBranch), HttpStatus.OK);
    }
}
