package ru.shvechkov.phonebook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.shvechkov.phonebook.model.Department;
import ru.shvechkov.phonebook.model.FilialBranch;

import java.util.List;

@Repository
public interface FilialBranchRepository extends JpaRepository<FilialBranch, Long> {

    public List<FilialBranch> findFilialBranchByFilialNameContaining(String nameFil);

}