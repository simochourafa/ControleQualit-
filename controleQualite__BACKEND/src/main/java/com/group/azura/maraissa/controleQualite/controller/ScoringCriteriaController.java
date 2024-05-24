package com.group.azura.maraissa.controleQualite.controller;

import com.group.azura.maraissa.controleQualite.entities.postgres.AttackLevel;
import com.group.azura.maraissa.controleQualite.entities.postgres.ScoringCriteria;
import com.group.azura.maraissa.controleQualite.interfaces.service.ScoringCriteriaInterface;
import com.group.azura.maraissa.controleQualite.service.DefectTypeService;
import com.group.azura.maraissa.controleQualite.service.ScoringCriteriaService;
import com.group.azura.maraissa.controleQualite.specification.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/scoringCriteria")
@RequiredArgsConstructor
public class ScoringCriteriaController implements ScoringCriteriaInterface {

    final ScoringCriteriaService scoringCriteriaService;

    @PostMapping("/find-with-filters")
    public ResponseEntity<Page<ScoringCriteria>> findWithFilters(@RequestBody  List<SearchCriteria> criterias,

                                                             @RequestParam(defaultValue = "1") Integer page,
                                                             @RequestParam(defaultValue = "9") Integer size,
                                                             @RequestParam(value = "sort[]", required = false) String[] sort) {
        return new ResponseEntity<Page<ScoringCriteria>>(scoringCriteriaService.findWithFilters(
                criterias, page, size, sort), HttpStatus.OK);
    }


    @GetMapping
    @Override
    public List<ScoringCriteria> getAll() {
        return scoringCriteriaService.getAll();
    }

    @GetMapping("/{id}")
    @Override
    public ScoringCriteria get(@PathVariable Long id) {
        return scoringCriteriaService.get(id);
    }

    @PostMapping
    @Override
    public ScoringCriteria create(@RequestBody ScoringCriteria scoringCriteria) {
        return scoringCriteriaService.create(scoringCriteria);
    }

    @PutMapping
    @Override
    public ScoringCriteria update(@RequestBody ScoringCriteria scoringCriteria) {
        return scoringCriteriaService.update(scoringCriteria);
    }

    @DeleteMapping("/{id}")
    @Override
    public void delete(Long id) {
        scoringCriteriaService.delete(id);
    }
}
