package com.group.azura.maraissa.controleQualite.repository.postgres;

import com.group.azura.maraissa.controleQualite.entities.postgres.AttackLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AttackLevelRepository extends JpaRepository<AttackLevel,Long> , JpaSpecificationExecutor<AttackLevel> {
}