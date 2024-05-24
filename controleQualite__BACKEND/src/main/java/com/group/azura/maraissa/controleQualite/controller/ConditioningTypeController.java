package com.group.azura.maraissa.controleQualite.controller;

import com.group.azura.maraissa.controleQualite.entities.postgres.Conditioning;
import com.group.azura.maraissa.controleQualite.entities.postgres.ConditioningType;
import com.group.azura.maraissa.controleQualite.interfaces.service.ConditioningTypeInterface;
import com.group.azura.maraissa.controleQualite.service.ConditioningService;
import com.group.azura.maraissa.controleQualite.service.ConditioningTypeService;
import com.group.azura.maraissa.controleQualite.specification.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/conditioningType")
@RequiredArgsConstructor
public class ConditioningTypeController implements ConditioningTypeInterface {

    final ConditioningTypeService conditioningTypeService;

    @PostMapping("/find-with-filters")
    public ResponseEntity<Page<ConditioningType>> findWithFilters(@RequestBody  List<SearchCriteria> criterias,

                                                              @RequestParam(defaultValue = "1") Integer page,
                                                              @RequestParam(defaultValue = "9") Integer size,
                                                              @RequestParam(value = "sort[]", required = false) String[] sort) {
        return new ResponseEntity<Page<ConditioningType>>(conditioningTypeService.findWithFilters(
                criterias, page, size, sort), HttpStatus.OK);
    }



    @GetMapping
    @Override
    public List<ConditioningType> getAll() {
        return  conditioningTypeService.getAll();
    }


    @GetMapping("/{id}")
    @Override
    public ConditioningType get(@PathVariable Long id) {
        return conditioningTypeService.get(id);
    }

    @PostMapping
    @Override
    public ConditioningType create(@RequestBody ConditioningType conditioningType) {
        return conditioningTypeService.create(conditioningType);
    }


    @PutMapping
    @Override
    public ConditioningType update(@RequestBody ConditioningType conditioningType) {
        return conditioningTypeService.update(conditioningType);
    }

    @DeleteMapping("/{id}")
    @Override
    public void delete(Long id) {
        conditioningTypeService.delete(id);
    }
}
