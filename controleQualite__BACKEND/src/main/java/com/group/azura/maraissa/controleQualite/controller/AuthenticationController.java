package com.group.azura.maraissa.controleQualite.controller;

import com.group.azura.maraissa.controleQualite.dto.AuthRequestDto;
import com.group.azura.maraissa.controleQualite.dto.AuthentificationResponseDto;
import com.group.azura.maraissa.controleQualite.service.AuthentificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthenticationController {
    final AuthentificationService authenticationService;


    @PostMapping("/authenticate")
    public AuthentificationResponseDto authenticateAndGetToken(@RequestBody AuthRequestDto authRequestdto) {
        return authenticationService.authenticateAndGetToken(authRequestdto);
    }
    @PostMapping("/refresh-token")
    public String refreshToken(@RequestHeader("Authorization") String token) {
        return authenticationService.refreshToken(token);
    }
}
