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
public class ScoringCriteria extends Auditable<String>{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private int note;
    @ManyToOne
    private Frequency frequencies;
    @ManyToOne
    private AttackLevel attackLevels;


}
