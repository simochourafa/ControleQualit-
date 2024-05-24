package com.group.azura.maraissa.controleQualite.service;

import com.group.azura.maraissa.controleQualite.entities.postgres.AppUser;
import com.group.azura.maraissa.controleQualite.entities.postgres.Frequency;
import com.group.azura.maraissa.controleQualite.entities.postgres.Grading;
import com.group.azura.maraissa.controleQualite.interfaces.service.GradingInterface;
import com.group.azura.maraissa.controleQualite.repository.postgres.GradingRepository;
import com.group.azura.maraissa.controleQualite.specification.BaseSpecification;
import com.group.azura.maraissa.controleQualite.specification.SearchCriteria;
import com.group.azura.maraissa.controleQualite.util.PaginationUtil;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class GradingService implements GradingInterface {
    private final GradingRepository gradingRepository;


    public Page<Grading> findWithFilters(List<SearchCriteria> criterias, Integer page,
                                         Integer size,
                                         String[] sortBy) {
        Specification<Grading> specification = new BaseSpecification<Grading>(criterias);

        Page<Grading> pageResult = gradingRepository.findAll(specification,
                PageRequest.of(page - 1, PaginationUtil.checkPaginationPageSize(size),
                        (PaginationUtil.getSortOrders(sortBy))));

        return pageResult;
    }

    public GradingService(GradingRepository gradingRepository) {
        this.gradingRepository = gradingRepository;
    }



    @Override
    public List<Grading> getAll(){return gradingRepository.findAll();}

    @Override
    public Grading get(Long id) {
        Optional<Grading> foundEntity = gradingRepository.findById(id);
        if(foundEntity.isPresent()){
            return  foundEntity.get();
        } else {
            throw new EntityNotFoundException("grading with id "+id+" not found !");
        }
    }

    @Override
    public Grading create(Grading entity) {
        return null;
    }

    @Override
    public Grading update(Grading entity) {
        return null;
    }

    @Override
    public void delete(Long aLong) {

    }
    //not


    @Override
    public Grading update(Long id, Grading gradingDetails) {
        return null;
    }
}