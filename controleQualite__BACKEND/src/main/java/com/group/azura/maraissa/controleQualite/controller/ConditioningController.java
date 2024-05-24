package com.group.azura.maraissa.controleQualite.controller;

import com.group.azura.maraissa.controleQualite.entities.postgres.AppUser;
import com.group.azura.maraissa.controleQualite.entities.postgres.Conditioning;
import com.group.azura.maraissa.controleQualite.interfaces.controller.ConditioningControllerInterface;
import com.group.azura.maraissa.controleQualite.service.AppUserService;
import com.group.azura.maraissa.controleQualite.service.ConditioningService;
import com.group.azura.maraissa.controleQualite.specification.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/conditioning")
@RequiredArgsConstructor
public class ConditioningController implements ConditioningControllerInterface {

    final ConditioningService conditioningService;
    @PostMapping("/find-with-filters")
    public ResponseEntity<Page<Conditioning>> findWithFilters(@RequestBody  List<SearchCriteria> criterias,

                                                         @RequestParam(defaultValue = "1") Integer page,
                                                         @RequestParam(defaultValue = "9") Integer size,
                                                         @RequestParam(value = "sort[]", required = false) String[] sort) {
        return new ResponseEntity<Page<Conditioning>>(conditioningService.findWithFilters(
                criterias, page, size, sort), HttpStatus.OK);
    }




    @GetMapping
    @Override
    public List<Conditioning> getAll() {
        return conditioningService.getAll();
    }


    @GetMapping("/{id}")
    @Override
    public Conditioning get(@PathVariable Long id) {
        return conditioningService.get(id);
    }

    @PostMapping
    @Override
    public Conditioning create(@RequestBody Conditioning conditioning) {
        return conditioningService.create(conditioning);
    }

    @PutMapping
    @Override
    public Conditioning update(@RequestBody Conditioning conditioning) {
        return conditioningService.update(conditioning);
    }


    @DeleteMapping("/{id}")
    @Override
    public void delete(@PathVariable Long id) {
        conditioningService.delete(id);
    }
}
