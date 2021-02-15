package ru.shvechkov.phonebook.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.shvechkov.phonebook.model.Organization;
import ru.shvechkov.phonebook.model.dto.EmployeeDto;
import ru.shvechkov.phonebook.model.dto.OrganizationDto;
import ru.shvechkov.phonebook.service.OrganizationService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/organization")
public class OrganizationController {



    private final OrganizationService organizationService;

    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @PostMapping
    public ResponseEntity<OrganizationDto> addOrganization(@RequestBody final OrganizationDto organizationDto){
        Organization organization = organizationService.addOrganization(Organization.fromDto(organizationDto));
        return new ResponseEntity<>(OrganizationDto.fromEntity(organization), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<OrganizationDto>> getOrganizations(){
        List<Organization> organizations = organizationService.getOrganizations();
        List<OrganizationDto> organizationsDto = organizations.stream().map(OrganizationDto::fromEntity).collect(Collectors.toList());
        return new ResponseEntity<>(organizationsDto, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<OrganizationDto> getOrganization(@PathVariable final Long id){
        Organization organization = organizationService.getOrganization(id);
        return new ResponseEntity<>(OrganizationDto.fromEntity(organization), HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<OrganizationDto> deleteOrganization(@PathVariable final Long id){
        Organization organization = organizationService.removeOrganization(id);
        return new ResponseEntity<>(OrganizationDto.fromEntity(organization), HttpStatus.OK);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<OrganizationDto> updateOrganization(@PathVariable final Long id,
                                                          @RequestBody final OrganizationDto organizationDto){
        Organization updatedOrganization = organizationService.updateOrganization(id, Organization.fromDto(organizationDto));
        return new ResponseEntity<>(OrganizationDto.fromEntity(updatedOrganization), HttpStatus.OK);
    }

    @GetMapping(value = "name/{nameOrg}")
    public ResponseEntity<List<OrganizationDto>> getOrganizationsByName(@PathVariable final String nameOrg){
        List<Organization> organizations = organizationService.findOrganizationsByNameOrg(nameOrg);
        List<OrganizationDto> organizationsDto = organizations.stream().map(OrganizationDto::fromEntity).collect(Collectors.toList());
        return new ResponseEntity<>(organizationsDto, HttpStatus.OK);
    }

    @GetMapping(value = "inn/{inn}")
    public ResponseEntity<List<OrganizationDto>> getOrganizationsByInn(@PathVariable final String inn){
        List<Organization> organizations = organizationService.findOrganizationsByInn(inn);
        List<OrganizationDto> organizationsDto = organizations.stream().map(OrganizationDto::fromEntity).collect(Collectors.toList());
        return new ResponseEntity<>(organizationsDto, HttpStatus.OK);
    }

    @PostMapping(value = "{organizationId}/employees/{employeeId}/add")
    public ResponseEntity<OrganizationDto> addEmployeeToOrganization(@PathVariable final Long organizationId,
                                                                     @PathVariable final Long employeeId,
                                                                     @RequestBody final EmployeeDto employeeDto){
        Organization organization = organizationService.addEmployeeToOrganization(employeeId, organizationId);
        return new ResponseEntity<>(OrganizationDto.fromEntity(organization), HttpStatus.OK);
    }

    @DeleteMapping(value = "{organizationId}/employees/{employeeId}/remove")
    public ResponseEntity<OrganizationDto> removeEmployeeFromOrganization(@PathVariable final Long organizationId,
                                                                        @PathVariable final Long employeeId){
        Organization organization = organizationService.removeEmployeeFromOrganization(employeeId, organizationId);
        return new ResponseEntity<>(OrganizationDto.fromEntity(organization), HttpStatus.OK);
    }

    @PostMapping(value = "{organizationId}/filialBranch/{filialBranchId}/add")
    public ResponseEntity<OrganizationDto> addFilialToOrganization(@PathVariable final Long organizationId,
                                                                   @PathVariable final Long filialBranchId,
                                                                   @RequestBody final EmployeeDto employeeDto){
        Organization organization = organizationService.addFilialBranchToOrganization(filialBranchId, organizationId);
        return new ResponseEntity<>(OrganizationDto.fromEntity(organization), HttpStatus.OK);
    }

    @DeleteMapping(value = "{organizationId}/filialBranch/{filialBranchId}/remove")
    public ResponseEntity<OrganizationDto> removeFilialFromOrganization(@PathVariable final Long organizationId,
                                                                        @PathVariable final Long filialBranchId){
        Organization organization = organizationService.removeFilialBranchFromOrganization(filialBranchId, organizationId);
        return new ResponseEntity<>(OrganizationDto.fromEntity(organization), HttpStatus.OK);
    }

    @PostMapping(value = "{organizationId}/department/{departmentId}/add")
    public ResponseEntity<OrganizationDto> addDepartmentToOrganization(@PathVariable final Long organizationId,
                                                                       @PathVariable final Long departmentId,
                                                                       @RequestBody final EmployeeDto employeeDto){
        Organization organization = organizationService.addDepartmentToOrganization(departmentId, organizationId);
        return new ResponseEntity<>(OrganizationDto.fromEntity(organization), HttpStatus.OK);
    }

    @DeleteMapping(value = "{organizationId}/department/{departmentId}/remove")
    public ResponseEntity<OrganizationDto> removeDepartmentFromOrganization(@PathVariable final Long organizationId,
                                                                            @PathVariable final Long departmentId){
        Organization organization = organizationService.removeDepartmentFromOrganization(departmentId, organizationId);
        return new ResponseEntity<>(OrganizationDto.fromEntity(organization), HttpStatus.OK);
    }
}
