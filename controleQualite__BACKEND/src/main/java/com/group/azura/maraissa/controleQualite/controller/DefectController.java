package com.group.azura.maraissa.controleQualite.controller;

import com.group.azura.maraissa.controleQualite.entities.postgres.Conditioning;
import com.group.azura.maraissa.controleQualite.entities.postgres.ConditioningType;
import com.group.azura.maraissa.controleQualite.entities.postgres.Defect;
import com.group.azura.maraissa.controleQualite.interfaces.controller.DefectControllerInterface;
import com.group.azura.maraissa.controleQualite.service.ConditioningTypeService;
import com.group.azura.maraissa.controleQualite.service.DefectService;
import com.group.azura.maraissa.controleQualite.specification.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/defect")
@RequiredArgsConstructor
public class DefectController implements DefectControllerInterface {
    final DefectService defectService;
    @PostMapping("/find-with-filters")
    public ResponseEntity<Page<Defect>> findWithFilters(@RequestBody  List<SearchCriteria> criterias,

                                                                  @RequestParam(defaultValue = "1") Integer page,
                                                                  @RequestParam(defaultValue = "9") Integer size,
                                                                  @RequestParam(value = "sort[]", required = false) String[] sort) {
        return new ResponseEntity<Page<Defect>>(defectService.findWithFilters(
                criterias, page, size, sort), HttpStatus.OK);
    }





    @GetMapping
    @Override
    public List<Defect> getAll() {
        return  defectService.getAll();
    }

    @GetMapping("/{id}")
    @Override
    public Defect get(@PathVariable Long id) {
        return defectService.get(id);
    }

    @PostMapping
    @Override
    public Defect create(@RequestBody Defect defect) {
        return defectService.create(defect);
    }



    @PutMapping
    @Override
    public Defect update(@RequestBody Defect defect) {
        return defectService.update(defect);
    }


    @DeleteMapping("/{id}")
    @Override
    public void delete(Long id) {
        defectService.delete(id);
    }
}
