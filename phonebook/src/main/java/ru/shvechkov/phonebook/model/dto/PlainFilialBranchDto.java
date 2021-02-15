package ru.shvechkov.phonebook.model.dto;

import lombok.Data;
import ru.shvechkov.phonebook.model.FilialBranch;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class PlainFilialBranchDto {

    private Long id;
    private String filialName;
    private String address;

    public static PlainFilialBranchDto fromEntity(FilialBranch filialBranch){
        PlainFilialBranchDto plainFilialBranchDto = new PlainFilialBranchDto();
        plainFilialBranchDto.setId(filialBranch.getId());
        plainFilialBranchDto.setFilialName(filialBranch.getFilialName());
        plainFilialBranchDto.setAddress(filialBranch.getAddress());
        return plainFilialBranchDto;
    }
}