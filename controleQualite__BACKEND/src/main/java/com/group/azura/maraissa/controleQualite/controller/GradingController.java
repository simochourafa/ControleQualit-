package com.group.azura.maraissa.controleQualite.controller;

import com.group.azura.maraissa.controleQualite.entities.postgres.Conditioning;
import com.group.azura.maraissa.controleQualite.entities.postgres.Grading;
import com.group.azura.maraissa.controleQualite.interfaces.controller.GradingControllerInterface;
import com.group.azura.maraissa.controleQualite.service.GradingService;
import com.group.azura.maraissa.controleQualite.specification.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/grading")
public class GradingController implements GradingControllerInterface {
    final GradingService gradingService;
    @PostMapping("/find-with-filters")
    public ResponseEntity<Page<Grading>> findWithFilters(@RequestBody  List<SearchCriteria> criterias,

                                                         @RequestParam(defaultValue = "1") Integer page,
                                                         @RequestParam(defaultValue = "9") Integer size,
                                                         @RequestParam(value = "sort[]", required = false) String[] sort) {
        return new ResponseEntity<Page<Grading>>(gradingService.findWithFilters(
                criterias, page, size, sort), HttpStatus.OK);
    }



    public GradingController(GradingService gradingService) {
        this.gradingService = gradingService;
    }

    @GetMapping("/")
    @Override
    public List<Grading> getAll() {
        return gradingService.getAll();
    }

    @GetMapping("/{id}")
    @Override
    public Grading get(@PathVariable Long id) {
        return gradingService.get(id);
    }




    @Override
    public Grading create(@RequestBody Grading grading) {
        return null;
    }


    @Override
    public Grading update(Grading grading) {
        return null;
    }



    @Override
    public void delete(Long id) {
        gradingService.delete(id);
    }




}
