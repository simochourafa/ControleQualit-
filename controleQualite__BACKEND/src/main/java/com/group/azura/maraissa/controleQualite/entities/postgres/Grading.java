package com.group.azura.maraissa.controleQualite.entities.postgres;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
@RequiredArgsConstructor
@Setter
@Getter
@Entity
public class Grading extends Auditable<String>{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long  id;
    private LocalDate date;
    private String placeOfControle;
    private int shipmentNumber;
    private int trailerNumber ;
    private int batchNumber;
    private int coupe;
    private boolean qsMarking ;
    private boolean complianceOfTheQsMark ;
    private int temperature ;
    private String coolness;
    @ManyToOne
    private AppUser appUsers;
    @ManyToOne
    private Conditioning conditionings;
    @ManyToOne
    private ScoringCriteria scoringCriterias;
    @ManyToOne
    private Client clients;
    @ManyToOne
    private Defect defects;
    @ManyToOne
    private Specie species;



    @OneToMany(mappedBy = "grading", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Picture> pictures;


}