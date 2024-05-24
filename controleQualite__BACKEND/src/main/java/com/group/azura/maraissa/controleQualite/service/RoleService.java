package com.group.azura.maraissa.controleQualite.service;

import com.group.azura.maraissa.controleQualite.entities.postgres.Role;
import com.group.azura.maraissa.controleQualite.interfaces.service.RoleInterface;
import com.group.azura.maraissa.controleQualite.repository.postgres.RoleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService implements RoleInterface {

    final RoleRepository roleRepository;
    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }


    @Override
    public Role get(Long id) {
        Optional<Role> foundEntity = roleRepository.findById(id);
        if(foundEntity.isPresent()){
            return foundEntity.get();
        } else {
            throw new EntityNotFoundException("Role with id "+id+"not found !");
        }
    }


    @Override
    public Role create(Role role) {
        return roleRepository.save(role);
    }


    @Override
    public Role update(Role role) {
        if(get(role.getId()) != null){
            return roleRepository.save(role);
        } else {
            return null;
        }
    }


    @Override
    public void delete(Long id) {
        roleRepository.delete(get(id));
    }
}
