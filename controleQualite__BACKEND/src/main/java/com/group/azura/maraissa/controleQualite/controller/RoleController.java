package com.group.azura.maraissa.controleQualite.controller;

import com.group.azura.maraissa.controleQualite.entities.postgres.Role;
import com.group.azura.maraissa.controleQualite.interfaces.controller.RoleControllerInterface;
import com.group.azura.maraissa.controleQualite.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/role")
@RequiredArgsConstructor
public class RoleController implements RoleControllerInterface {

    final RoleService roleService;


    @GetMapping
    @Override
    public List<Role> getAll() {
        return roleService.getAll();
    }


    @GetMapping("/{id}")
    @Override
    public Role get(@PathVariable Long id) {
        return roleService.get(id);
    }


    @PostMapping
    @Override
    public Role create(@RequestBody Role role) {
        return roleService.create(role);
    }


    @PutMapping
    @Override
    public Role update(@RequestBody Role role) {
        return roleService.update(role);
    }


    @DeleteMapping("/{id}")
    @Override
    public void delete(@PathVariable Long id) {
        roleService.delete(id);
    }
}
