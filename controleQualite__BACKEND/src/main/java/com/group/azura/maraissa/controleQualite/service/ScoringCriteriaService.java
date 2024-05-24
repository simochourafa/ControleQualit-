package com.group.azura.maraissa.controleQualite.service;

import com.group.azura.maraissa.controleQualite.entities.postgres.AttackLevel;
import com.group.azura.maraissa.controleQualite.entities.postgres.Frequency;
import com.group.azura.maraissa.controleQualite.entities.postgres.ScoringCriteria;
import com.group.azura.maraissa.controleQualite.interfaces.service.ScoringCriteriaInterface;
import com.group.azura.maraissa.controleQualite.repository.postgres.DefectRepository;
import com.group.azura.maraissa.controleQualite.repository.postgres.ScoringCriteriaRepository;
import com.group.azura.maraissa.controleQualite.specification.BaseSpecification;
import com.group.azura.maraissa.controleQualite.specification.SearchCriteria;
import com.group.azura.maraissa.controleQualite.util.PaginationUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ScoringCriteriaService implements ScoringCriteriaInterface {

    final ScoringCriteriaRepository scoringCriteriaRepository;
    public Page<ScoringCriteria> findWithFilters(List<SearchCriteria> criterias, Integer page,
                                             Integer size,
                                             String[] sortBy) {
        Specification<ScoringCriteria> specification = new BaseSpecification<ScoringCriteria>(criterias);

        Page<ScoringCriteria> pageResult = scoringCriteriaRepository.findAll(specification,
                PageRequest.of(page - 1, PaginationUtil.checkPaginationPageSize(size),
                       (PaginationUtil.getSortOrders(sortBy))));

        return pageResult;
    }


    @Override
    public List<ScoringCriteria> getAll() {

        return scoringCriteriaRepository.findAll();
    }

    @Override
    public ScoringCriteria get(Long id)
    {
        Optional<ScoringCriteria> foundEntity = scoringCriteriaRepository.findById(id);
        if(foundEntity.isPresent()){
            return  foundEntity.get();
        } else {
            throw new EntityNotFoundException("scoring criteria with id "+id+" not found !");
        }
    }

    @Override
    public ScoringCriteria create(ScoringCriteria scoringCriteria) {

        return scoringCriteriaRepository.save(scoringCriteria);
    }

    @Override
    public ScoringCriteria update(ScoringCriteria scoringCriteria) {

        if(get(scoringCriteria.getId()) != null){
            return scoringCriteriaRepository.save(scoringCriteria);
        } else {
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        scoringCriteriaRepository.delete(get(id));

    }
}
