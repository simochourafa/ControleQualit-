package com.group.azura.maraissa.controleQualite.service;

import com.group.azura.maraissa.controleQualite.entities.postgres.AttackLevel;
import com.group.azura.maraissa.controleQualite.entities.postgres.Conditioning;
import com.group.azura.maraissa.controleQualite.entities.postgres.Frequency;
import com.group.azura.maraissa.controleQualite.interfaces.service.AttackLevelInterface;
import com.group.azura.maraissa.controleQualite.repository.postgres.AttackLevelRepository;
import com.group.azura.maraissa.controleQualite.repository.postgres.ConditioningRepository;
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
public class AttackLevelService implements AttackLevelInterface {
    final AttackLevelRepository attackLevelRepository;
    public Page<AttackLevel> findWithFilters(List<SearchCriteria> criterias, Integer page,
                                           Integer size,
                                           String[] sortBy) {
        Specification<AttackLevel> specification = new BaseSpecification<AttackLevel>(criterias);

        Page<AttackLevel> pageResult = attackLevelRepository.findAll(specification,
                PageRequest.of(page - 1, PaginationUtil.checkPaginationPageSize(size),
                        (PaginationUtil.getSortOrders(sortBy))));

        return pageResult;
    }


    @Override
    public List<AttackLevel> getAll() {

        return attackLevelRepository.findAll();
    }

    @Override
    public AttackLevel get(Long id) {
        Optional<AttackLevel> foundEntity = attackLevelRepository.findById(id);
        if(foundEntity.isPresent()){
            return  foundEntity.get();
        } else {
            throw new EntityNotFoundException("Attack level with id "+id+" not found !");
        }
    }

    @Override
    public AttackLevel create(AttackLevel attackLevel)
    {
        return  attackLevelRepository.save(attackLevel);
    }

    @Override
    public AttackLevel update(AttackLevel attackLevel) {

        if(get(attackLevel.getId()) != null){
            return attackLevelRepository.save(attackLevel);
        } else {
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        attackLevelRepository.delete(get(id));
    }
}
