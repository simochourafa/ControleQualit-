package com.group.azura.maraissa.controleQualite.service;

import com.group.azura.maraissa.controleQualite.dto.ActionDto;
import com.group.azura.maraissa.controleQualite.dto.MenuDto;
import com.group.azura.maraissa.controleQualite.dto.exception.EntityAlreadyExistsException;
import com.group.azura.maraissa.controleQualite.entities.postgres.*;
import com.group.azura.maraissa.controleQualite.interfaces.service.MenuInterface;
import com.group.azura.maraissa.controleQualite.repository.postgres.ActionRepository;
import com.group.azura.maraissa.controleQualite.repository.postgres.MenuRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenuService implements MenuInterface {
    final MenuRepository menusRepository;
    @Override
    public List<Menu> getAll() {
        return menusRepository.findAll();
    }

    @Override
    public Menu get(Long id) {
        Optional<Menu> foundEntity = menusRepository.findById(id);
        if(foundEntity.isPresent()){
            return foundEntity.get();
        } else {
            throw new EntityNotFoundException("Menus with id "+id+" not found !");
        }

    }

    @Override
    public Menu create(Menu menus) {

        Optional<Menu> foundEntity = menusRepository.findById(menus.getId());
        if(foundEntity.isPresent()){
            throw new EntityAlreadyExistsException("Menus with id "+menus.getId()+" already exist");
        } else {
            return menusRepository.save(menus);
        }
    }

    @Override
    public Menu update(Menu menus) {
        if(get(menus.getId()) != null){
            return menusRepository.save(menus);
        } else {
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        menusRepository.delete(get(id));

    }

}
