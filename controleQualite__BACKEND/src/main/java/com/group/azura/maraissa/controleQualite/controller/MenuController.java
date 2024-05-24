package com.group.azura.maraissa.controleQualite.controller;

import com.group.azura.maraissa.controleQualite.entities.postgres.Menu;
import com.group.azura.maraissa.controleQualite.interfaces.controller.MenuControllerInterface;
import com.group.azura.maraissa.controleQualite.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menus")
@RequiredArgsConstructor
public class MenuController implements MenuControllerInterface {
    final MenuService menusService;

    @GetMapping
    @Override
    public List<Menu> getAll() {
        return menusService.getAll();
    }

    @GetMapping("/{id}")
    @Override
    public Menu get(@PathVariable Long id) {
        return menusService.get(id);
    }

    @PostMapping
    @Override
    public Menu create(@RequestBody Menu menu) {
        return menusService.create(menu);
    }

    @PutMapping
    @Override
    public Menu update(@RequestBody Menu menu) {
        return menusService.update(menu);
    }

    @DeleteMapping("/{id}")
    @Override
    public void delete(@PathVariable Long id) {
        menusService.delete(id);
    }
}
