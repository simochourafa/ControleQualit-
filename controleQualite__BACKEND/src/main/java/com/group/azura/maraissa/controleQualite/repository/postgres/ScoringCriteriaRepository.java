package com.group.azura.maraissa.controleQualite.repository.postgres;

import com.group.azura.maraissa.controleQualite.entities.postgres.ScoringCriteria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ScoringCriteriaRepository extends JpaRepository<ScoringCriteria,Long> , JpaSpecificationExecutor<ScoringCriteria> {
}
