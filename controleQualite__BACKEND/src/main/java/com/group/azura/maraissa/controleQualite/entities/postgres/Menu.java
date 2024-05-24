package com.group.azura.maraissa.controleQualite.entities.postgres;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Menu extends Auditable<String>  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String name;

    @Column(unique = true)
    private String label;

    @Column(unique = true)
    private String url;

    private String icon;

    private int sort;

    @Column(columnDefinition = "boolean default false")
    private boolean isparent;


    @OneToMany
    private List<Menu> children;

}