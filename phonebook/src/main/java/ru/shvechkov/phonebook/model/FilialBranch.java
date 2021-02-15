package ru.shvechkov.phonebook.model;

import lombok.Data;
import ru.shvechkov.phonebook.model.dto.FilialBranchDto;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Entity
@Table(name = "filial_branch")
public class FilialBranch {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String filialName;
    private String address;

    @ManyToOne
    private Organization organization;

    @OneToMany(cascade = CascadeType.MERGE)
    @JoinColumn(name = "filial_branch_id")
    private List<Department> departments = new ArrayList<>();

    public FilialBranch(){
    }

    public static FilialBranch fromDto(FilialBranchDto filialBranchDto){
        FilialBranch filialBranch = new FilialBranch();
        filialBranch.setFilialName(filialBranchDto.getFilialName());
        filialBranch.setAddress(filialBranchDto.getAddress());
        filialBranch.setDepartments(filialBranchDto.getDepartmentsDto().stream().map(Department::fromDto).collect(Collectors.toList()));
        return filialBranch;
    }

    public void addDepartment(Department department){
        departments.add(department);
    }

    public void removeDepartment(Department department){
        departments.remove(department);
    }
}
