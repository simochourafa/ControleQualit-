package com.group.azura.maraissa.controleQualite.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuDto {
    private long id;
    private String name;
    private String label;
    private String url;
    private String icon;
    private int sort;
    private boolean isparent;
    private List<MenuDto> children;
    private List<ActionDto> actions;
}
