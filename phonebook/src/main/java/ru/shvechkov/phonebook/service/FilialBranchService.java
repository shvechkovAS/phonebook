package ru.shvechkov.phonebook.service;


import org.springframework.stereotype.Service;
import ru.shvechkov.phonebook.model.Department;
import ru.shvechkov.phonebook.model.FilialBranch;
import ru.shvechkov.phonebook.repository.FilialBranchRepository;
import javax.transaction.Transactional;
import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class FilialBranchService {

    private final FilialBranchRepository filialBranchRepository;
    private final DepartmentService departmentService;


    public FilialBranchService(FilialBranchRepository filialBranchRepository, DepartmentService departmentService) {
        this.filialBranchRepository = filialBranchRepository;
        this.departmentService = departmentService;
    }

    public FilialBranch addFilialBranch(FilialBranch filialBranch){
        return filialBranchRepository.save(filialBranch);
    }

    public List<FilialBranch> getFilialBranches(){
        return StreamSupport
                .stream(filialBranchRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public FilialBranch getFilialBranch(Long id){
        return filialBranchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Не удалось найти филиал с id = " + id));
    }

    public List<FilialBranch> getFilialBranchesByName(String filName){
        return filialBranchRepository.findFilialBranchByFilialNameContaining(filName);
    }

    public FilialBranch removeFilialBranch(Long id){
        FilialBranch filialBranch = getFilialBranch(id);
        filialBranchRepository.delete(filialBranch);
        return filialBranch;
    }

    @Transactional
    public FilialBranch updateFilialBranch(Long id, FilialBranch filialBranch){
        FilialBranch filialBranchToEdit = getFilialBranch(id);
        filialBranchToEdit.setFilialName(filialBranch.getFilialName());
        filialBranchToEdit.setAddress(filialBranch.getAddress());
        filialBranchToEdit.setDepartments(filialBranch.getDepartments());
        return filialBranchToEdit;
    }

    @Transactional
    public FilialBranch addDepartmentToFilialBranch(Long filialBranchId, Long departmentId){
        FilialBranch filialBranch = getFilialBranch(filialBranchId);
        Department department = departmentService.getDepartment(departmentId);
        if (Objects.nonNull(department.getFilialBranch())) {
            throw new
                    RuntimeException(MessageFormat.format("Отдел {0} уже принадлежит филиалу {1}",departmentId,
                    department.getFilialBranch().getId()));
        }
        filialBranch.addDepartment(department);
        department.setFilialBranch(filialBranch);
        return filialBranch;
    }

    @Transactional
    public FilialBranch removeDepartmentFromFilialBranch(Long filialBranchId, Long departmentId){
        Department department = departmentService.getDepartment(departmentId);
        FilialBranch filialBranch = getFilialBranch(filialBranchId);
        filialBranch.removeDepartment(department);
        return filialBranch;
    }
}
