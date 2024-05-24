package com.group.azura.maraissa.controleQualite.controller;

import com.group.azura.maraissa.controleQualite.entities.postgres.ScoringCriteria;
import com.group.azura.maraissa.controleQualite.entities.postgres.Specie;
import com.group.azura.maraissa.controleQualite.interfaces.service.SpecieInterface;
import com.group.azura.maraissa.controleQualite.service.ScoringCriteriaService;
import com.group.azura.maraissa.controleQualite.service.SpecieService;
import com.group.azura.maraissa.controleQualite.specification.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/specie")
@RequiredArgsConstructor
public class SpecieController implements SpecieInterface {

    final SpecieService specieService;
    @PostMapping("/find-with-filters")
    public ResponseEntity<Page<Specie>> findWithFilters(@RequestBody  List<SearchCriteria> criterias,

                                                                 @RequestParam(defaultValue = "1") Integer page,
                                                                 @RequestParam(defaultValue = "9") Integer size,
                                                                 @RequestParam(value = "sort[]", required = false) String[] sort) {
        return new ResponseEntity<Page<Specie>>(specieService.findWithFilters(
                criterias, page, size, sort), HttpStatus.OK);
    }


    @GetMapping
    @Override
    public List<Specie> getAll() {
        return specieService.getAll();
    }


    @GetMapping("/{id}")
    @Override
    public Specie get(@PathVariable Long id) {
        return specieService.get(id);
    }


    @PostMapping
    @Override
    public Specie create(@RequestBody Specie specie) {
        return specieService.create(specie);
    }

    @PutMapping
    @Override
    public Specie update(@RequestBody Specie specie) {
        return specieService.update(specie);
    }

    @DeleteMapping("/{id}")
    @Override
    public void delete(Long id) {
        specieService.delete(id);
    }
}
