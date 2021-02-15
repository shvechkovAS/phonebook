package ru.shvechkov.phonebook.model.dto;

import lombok.Data;
import ru.shvechkov.phonebook.model.Organization;


@Data
public class PlainOrgDto {

    private Long id;
    private String nameOrg;
    private String directorName;
    private String inn;
    private String kpp;
    private String address;

    public static PlainOrgDto fromEntity(Organization organization) {
        PlainOrgDto plainOrgDto = new PlainOrgDto();
        plainOrgDto.setId(organization.getId());
        plainOrgDto.setNameOrg(organization.getNameOrg());
        plainOrgDto.setDirectorName(organization.getDirectorName());
        plainOrgDto.setInn(organization.getInn());
        plainOrgDto.setKpp(organization.getKpp());
        plainOrgDto.setAddress(organization.getAddress());
        return plainOrgDto;
    }
}
