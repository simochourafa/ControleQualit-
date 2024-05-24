package com.group.azura.maraissa.controleQualite.entities.postgres;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;
@RequiredArgsConstructor
@Setter
@Getter
@Entity
public class Defect extends Auditable<String>{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String defectName;
    @ManyToOne
    private DefectType defectTypes;
}
