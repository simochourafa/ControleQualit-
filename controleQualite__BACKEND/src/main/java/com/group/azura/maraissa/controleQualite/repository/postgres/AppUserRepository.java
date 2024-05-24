package com.group.azura.maraissa.controleQualite.repository.postgres;

import com.group.azura.maraissa.controleQualite.entities.postgres.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser,String>, JpaSpecificationExecutor<AppUser> {
    Optional<AppUser> findByUsernameIgnoreCase(String username);
    List<AppUser> findByRolesName(String roleName);

}
