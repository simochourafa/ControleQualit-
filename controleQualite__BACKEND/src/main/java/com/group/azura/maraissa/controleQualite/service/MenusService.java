package com.group.azura.maraissa.controleQualite.service;

import com.group.azura.maraissa.controleQualite.dto.ActionDto;
import com.group.azura.maraissa.controleQualite.dto.MenuDto;
import com.group.azura.maraissa.controleQualite.entities.postgres.*;
import com.group.azura.maraissa.controleQualite.repository.postgres.ActionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MenusService {
    final ActionRepository actionRepository;

    public List<MenuDto> menuForAppUser(AppUser user) {

        List<MenuDto> menuDtos = new ArrayList<>();

        // Foreach AppUser roles to get All menus
        user.getRoles().forEach(userRole -> {

            for (Permission permission : userRole.getPermissions()) {
                if (permission.getMenu().isIsparent()) {
                    menuDtos.add(mapMenuToDto(permission.getMenu(), userRole));
                }
            }

        });

        return menuDtos;
    }




    private MenuDto mapMenuToDto(Menu menu, Role role) {
        MenuDto menuDto = new MenuDto();
        menuDto.setId(menu.getId());
        menuDto.setName(menu.getName());
        menuDto.setLabel(menu.getLabel());
        menuDto.setUrl(menu.getUrl());
        menuDto.setIcon(menu.getIcon());
        menuDto.setSort(menu.getSort());
        menuDto.setIsparent(menu.isIsparent());
        List<MenuDto> childrenDto = new ArrayList<>();
        for (Menu child : menu.getChildren()) {
            childrenDto.add(mapMenuToDto(child, role));
        }
        menuDto.setChildren(childrenDto);
        menuDto.setActions(mapActionsToDto(actionRepository.findActionsOfMenuAndrole(menu, role)));
        return menuDto;
    }

    private List<ActionDto> mapActionsToDto(List<Action> actions) {
        List<ActionDto> actionDtos = new ArrayList<>();
        for (Action action : actions) {
            ActionDto actionDto = new ActionDto();
            actionDto.setId(action.getId());
            actionDto.setName(action.getName());
            actionDtos.add(actionDto);
        }
        return actionDtos;
    }
}
