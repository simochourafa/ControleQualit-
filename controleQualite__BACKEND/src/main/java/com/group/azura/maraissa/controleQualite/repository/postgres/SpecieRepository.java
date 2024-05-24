package com.group.azura.maraissa.controleQualite.repository.postgres;

import com.group.azura.maraissa.controleQualite.entities.postgres.Specie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SpecieRepository extends JpaRepository<Specie,Long> , JpaSpecificationExecutor<Specie> {
}
