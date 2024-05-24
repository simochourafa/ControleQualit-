package com.group.azura.maraissa.controleQualite.controller;

import com.group.azura.maraissa.controleQualite.entities.postgres.Defect;
import com.group.azura.maraissa.controleQualite.entities.postgres.DefectType;
import com.group.azura.maraissa.controleQualite.interfaces.controller.DefectControllerInterface;
import com.group.azura.maraissa.controleQualite.interfaces.controller.DefectTypeControllerInterface;
import com.group.azura.maraissa.controleQualite.service.DefectService;
import com.group.azura.maraissa.controleQualite.service.DefectTypeService;
import com.group.azura.maraissa.controleQualite.specification.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/defectType")
@RequiredArgsConstructor
public class DefectTypeController implements DefectTypeControllerInterface {
    final DefectTypeService defectTypeService;

    @PostMapping("/find-with-filters")
    public ResponseEntity<Page<DefectType>> findWithFilters(@RequestBody  List<SearchCriteria> criterias,

                                                        @RequestParam(defaultValue = "1") Integer page,
                                                        @RequestParam(defaultValue = "9") Integer size,
                                                        @RequestParam(value = "sort[]", required = false) String[] sort) {
        return new ResponseEntity<Page<DefectType>>(defectTypeService.findWithFilters(
                criterias, page, size, sort), HttpStatus.OK);
    }




    @GetMapping
    @Override
    public List<DefectType> getAll() {
        return defectTypeService.getAll();
    }


    @GetMapping("/{id}")
    @Override
    public DefectType get(@PathVariable Long id) {
        return defectTypeService.get(id);
    }


    @PostMapping
    @Override
    public DefectType create(@RequestBody DefectType defectType) {
        return defectTypeService.create(defectType);
    }


    @PutMapping
    @Override
    public DefectType update(@RequestBody DefectType defectType) {
        return defectTypeService.update(defectType);
    }


    @DeleteMapping("/{id}")
    @Override
    public void delete(Long id) {
        defectTypeService.delete(id);
    }
}
