package com.group.azura.maraissa.controleQualite.service;

import com.group.azura.maraissa.controleQualite.entities.postgres.AppUser;
import com.group.azura.maraissa.controleQualite.repository.postgres.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
@RequiredArgsConstructor
public class AppUserDetailsService implements UserDetailsService{
    final AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> userInfo = appUserRepository.findByUsernameIgnoreCase(username);

        return userInfo
                .orElseThrow(() -> new UsernameNotFoundException("user not found " + username));

    }
}
