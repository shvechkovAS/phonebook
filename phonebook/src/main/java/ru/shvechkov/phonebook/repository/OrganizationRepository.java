package ru.shvechkov.phonebook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.shvechkov.phonebook.model.Organization;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrganizationRepository  extends JpaRepository<Organization, Long> {

    List<Organization> findOrganizationByNameOrgContaining(String nameOrg);

    List<Organization> findOrganizationByInnContaining(String inn);
}
