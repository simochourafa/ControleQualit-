package com.group.azura.maraissa.controleQualite.service;

import com.group.azura.maraissa.controleQualite.entities.postgres.AppUser;
import com.group.azura.maraissa.controleQualite.interfaces.service.AppUserInterface;
import com.group.azura.maraissa.controleQualite.repository.postgres.AppUserRepository;
import com.group.azura.maraissa.controleQualite.specification.BaseSpecification;
import com.group.azura.maraissa.controleQualite.specification.SearchCriteria;
import com.group.azura.maraissa.controleQualite.util.PaginationUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AppUserService implements AppUserInterface {
    final AppUserRepository appUserRepository;
   final PasswordEncoder passwordEncoder;


    public Page<AppUser> findWithFilters(List<SearchCriteria> criterias, Integer page,
                                         Integer size,
                                         String[] sortBy) {
        Specification<AppUser> specification = new BaseSpecification<AppUser>(criterias);

        Page<AppUser> pageResult = appUserRepository.findAll(specification,
                PageRequest.of(page - 1, PaginationUtil.checkPaginationPageSize(size),
                        (PaginationUtil.getSortOrders(sortBy))));

        return pageResult;
    }




    @Override
    public List<AppUser> getAll() {
        return appUserRepository.findAll();
    }

    @Override
    public AppUser get(String username) {
        Optional<AppUser> foundEntity = appUserRepository.findById(username);
        if(!foundEntity.isPresent()){
            throw new EntityNotFoundException("User with username "+username+" not found !");
        }
        return  foundEntity.get();
    }


    @Override
    public AppUser create(AppUser appUser) {
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        return appUserRepository.save(appUser);
    }

    @Override
    public AppUser update(AppUser appUser) {
        if(get(appUser.getUsername()) != null){
            appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
            return appUserRepository.save(appUser);
        } else {
            return null;
        }
    }

    @Override
    public void delete(String username) {
        appUserRepository.delete(get(username));
    }


    public List<AppUser> getUserByRoleName(String roleName) {
        return appUserRepository.findByRolesName(roleName);
    }
}
