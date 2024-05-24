package com.group.azura.maraissa.controleQualite.repository.postgres;

import com.group.azura.maraissa.controleQualite.entities.postgres.Conditioning;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ConditioningRepository extends JpaRepository<Conditioning,Long> , JpaSpecificationExecutor<Conditioning> {
}