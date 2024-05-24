package com.group.azura.maraissa.controleQualite.interfaces.service;

import com.group.azura.maraissa.controleQualite.entities.postgres.Grading;
import com.group.azura.maraissa.controleQualite.interfaces.CrudInterface;

public interface GradingInterface extends CrudInterface<Grading, Long> {
    Grading update(Long id, Grading gradingDetails);
}
