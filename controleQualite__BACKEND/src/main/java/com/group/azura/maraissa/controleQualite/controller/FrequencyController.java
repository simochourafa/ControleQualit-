package com.group.azura.maraissa.controleQualite.controller;

import com.group.azura.maraissa.controleQualite.entities.postgres.DefectType;
import com.group.azura.maraissa.controleQualite.entities.postgres.Frequency;
import com.group.azura.maraissa.controleQualite.interfaces.service.FrequencyInterface;
import com.group.azura.maraissa.controleQualite.service.DefectTypeService;
import com.group.azura.maraissa.controleQualite.service.FrequencyService;
import com.group.azura.maraissa.controleQualite.specification.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/frequency")
@RequiredArgsConstructor
public class FrequencyController implements FrequencyInterface {


    final FrequencyService frequencyService;

    @PostMapping("/find-with-filters")
    public ResponseEntity<Page<Frequency>> findWithFilters(@RequestBody  List<SearchCriteria> criterias,

                                                            @RequestParam(defaultValue = "1") Integer page,
                                                            @RequestParam(defaultValue = "9") Integer size,
                                                            @RequestParam(value = "sort[]", required = false) String[] sort) {
        return new ResponseEntity<Page<Frequency>>(frequencyService.findWithFilters(
                criterias, page, size, sort), HttpStatus.OK);
    }



    @GetMapping
    @Override
    public List<Frequency> getAll() {
        return frequencyService.getAll();
    }

    @GetMapping("/{id}")
    @Override
    public Frequency get(@PathVariable Long id) {
        return frequencyService.get(id);
    }


    @PostMapping
    @Override
    public Frequency create(@RequestBody Frequency frequency) {
        return frequencyService.create(frequency);
    }


    @PutMapping
    @Override
    public Frequency update(@RequestBody Frequency frequency) {
        return frequencyService.update(frequency);
    }

    @DeleteMapping("/{id}")
    @Override
    public void delete(Long id) {
        frequencyService.delete(id);
    }
}
