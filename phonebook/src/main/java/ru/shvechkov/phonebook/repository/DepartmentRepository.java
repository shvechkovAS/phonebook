package ru.shvechkov.phonebook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.shvechkov.phonebook.model.Department;
import ru.shvechkov.phonebook.model.dto.DepartmentDto;

import java.util.List;

@Repository
public interface DepartmentRepository  extends JpaRepository<Department, Long> {

    public List<Department> findDepartmentByDepNameContaining(String nameDep);
}
