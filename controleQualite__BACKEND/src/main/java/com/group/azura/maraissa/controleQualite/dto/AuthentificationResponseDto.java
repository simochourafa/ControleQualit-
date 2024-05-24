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
public class AuthentificationResponseDto {
    private UserDto user;
    private String token;
    private String refreshToken;
    private List<MenuDto> menus;
}
