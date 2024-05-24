package com.group.azura.maraissa.controleQualite.controller;

import com.group.azura.maraissa.controleQualite.entities.postgres.AttackLevel;
import com.group.azura.maraissa.controleQualite.entities.postgres.Frequency;
import com.group.azura.maraissa.controleQualite.interfaces.service.AttackLevelInterface;
import com.group.azura.maraissa.controleQualite.service.AppUserService;
import com.group.azura.maraissa.controleQualite.service.AttackLevelService;
import com.group.azura.maraissa.controleQualite.specification.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/attackLevel")
@RequiredArgsConstructor
public class AttackLevelController implements AttackLevelInterface {
    final AttackLevelService attackLevelService;

    @PostMapping("/find-with-filters")
    public ResponseEntity<Page<AttackLevel>> findWithFilters(@RequestBody  List<SearchCriteria> criterias,

                                                           @RequestParam(defaultValue = "1") Integer page,
                                                           @RequestParam(defaultValue = "9") Integer size,
                                                           @RequestParam(value = "sort[]", required = false) String[] sort) {
        return new ResponseEntity<Page<AttackLevel>>(attackLevelService.findWithFilters(
                criterias, page, size, sort), HttpStatus.OK);
    }

    @GetMapping
    @Override
    public List<AttackLevel> getAll() {
       return attackLevelService.getAll();
    }
    @GetMapping("/{id}")
    @Override
    public AttackLevel get(@PathVariable Long id) {
        return attackLevelService.get(id);
    }
    @PostMapping
    @Override
    public AttackLevel create(@RequestBody AttackLevel attackLevel) {
        return attackLevelService.create(attackLevel);
    }

    @PutMapping
    @Override
    public AttackLevel update(@RequestBody AttackLevel attackLevel) {
        return attackLevelService.update(attackLevel);
    }

    @DeleteMapping("/{id}")
    @Override
    public void delete(@PathVariable Long id) {
        attackLevelService.delete(id);
    }
}
