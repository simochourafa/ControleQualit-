package com.group.azura.maraissa.controleQualite.service;

import com.group.azura.maraissa.controleQualite.dto.AuthRequestDto;
import com.group.azura.maraissa.controleQualite.dto.AuthentificationResponseDto;
import com.group.azura.maraissa.controleQualite.dto.UserDto;
import com.group.azura.maraissa.controleQualite.entities.postgres.AppUser;
import com.group.azura.maraissa.controleQualite.entities.postgres.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthentificationService {
    final AppUserService appUserService;
    final PasswordEncoder passwordEncoder;
    final AuthenticationManager authenticationManager;
    final JwtService jwtService;
    final MenusService menusService;


    public AuthentificationResponseDto authenticateAndGetToken(AuthRequestDto authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));





        if (authentication.isAuthenticated()) {
            AppUser foundUser = appUserService.get(authRequest.getUsername());
            AuthentificationResponseDto responseDto = new AuthentificationResponseDto();

// Map AppUser to user Dto
            UserDto userdto = UserDto.builder()
                    .email(foundUser.getEmail())
                    .roles(foundUser.getRoles().stream().map(Role::getName).toList())
                    .username(foundUser.getUsername()).build();
            responseDto.setUser(userdto);

            // set menus for demo user
            responseDto.setMenus(menusService.menuForAppUser(foundUser));


            // set tokens
            responseDto.setToken( jwtService.generateToken(authRequest.getUsername()));

            return responseDto;




        }

        throw new UsernameNotFoundException("invalid user details.");
    }


    public String refreshToken(String token) {
        return jwtService.refreshToken(token);
    }
}
