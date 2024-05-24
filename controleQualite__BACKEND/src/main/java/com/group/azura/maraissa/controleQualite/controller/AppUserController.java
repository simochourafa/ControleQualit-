package com.group.azura.maraissa.controleQualite.controller;

import com.group.azura.maraissa.controleQualite.entities.postgres.AppUser;
import com.group.azura.maraissa.controleQualite.entities.postgres.Grading;
import com.group.azura.maraissa.controleQualite.interfaces.controller.AppUserControllerInterface;
import com.group.azura.maraissa.controleQualite.service.AppUserService;
import com.group.azura.maraissa.controleQualite.specification.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
//@PreAuthorize("hasAnyAuthority('ADMIN', 'CLIENT')")
@RequestMapping("/api/appUser")
@RequiredArgsConstructor
public class AppUserController implements AppUserControllerInterface {

    final AppUserService appUserService;
    @PostMapping("/find-with-filters")
    public ResponseEntity<Page<AppUser>> findWithFilters(@RequestBody  List<SearchCriteria> criterias,

                                                         @RequestParam(defaultValue = "1") Integer page,
                                                         @RequestParam(defaultValue = "9") Integer size,
                                                         @RequestParam(value = "sort[]", required = false) String[] sort) {
        return new ResponseEntity<Page<AppUser>>(appUserService.findWithFilters(
                criterias, page, size, sort), HttpStatus.OK);
    }



    @GetMapping
    @Override
    public List<AppUser> getAll() {
        return  appUserService.getAll();
    }

    @GetMapping("/{username}")
    @Override
    public AppUser get(@PathVariable String username) {
        return appUserService.get(username);
    }

    @PostMapping
    @Override
    public AppUser create(@RequestBody AppUser appUser) {
        return appUserService.create(appUser);
    }


    @PutMapping
    @Override
    public AppUser update(@RequestBody AppUser appUser) {
        return appUserService.update(appUser);
    }


    @DeleteMapping("/{username}")
    @Override
    public void delete(@PathVariable String username) {
        appUserService.delete(username);
    }

    @GetMapping("/GetUsersWithRoleAgreeur")
    public List<AppUser> getUserByRoleName() {
        return appUserService.getUserByRoleName("agreeur");
    }
}
