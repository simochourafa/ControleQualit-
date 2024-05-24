package com.group.azura.maraissa.controleQualite.service;

import com.group.azura.maraissa.controleQualite.entities.postgres.Conditioning;
import com.group.azura.maraissa.controleQualite.entities.postgres.ConditioningType;
import com.group.azura.maraissa.controleQualite.interfaces.service.ConditioningTypeInterface;
import com.group.azura.maraissa.controleQualite.repository.postgres.ConditioningRepository;
import com.group.azura.maraissa.controleQualite.repository.postgres.ConditioningTypeRepository;
import com.group.azura.maraissa.controleQualite.specification.BaseSpecification;
import com.group.azura.maraissa.controleQualite.specification.SearchCriteria;
import com.group.azura.maraissa.controleQualite.util.PaginationUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ConditioningTypeService implements ConditioningTypeInterface {

    final ConditioningTypeRepository conditioningTypeRepository;
    public Page<ConditioningType> findWithFilters(List<SearchCriteria> criterias, Integer page,
                                              Integer size,
                                              String[] sortBy) {
        Specification<ConditioningType> specification = new BaseSpecification<ConditioningType>(criterias);

        Page<ConditioningType> pageResult = conditioningTypeRepository.findAll(specification,
                PageRequest.of(page - 1, PaginationUtil.checkPaginationPageSize(size),
                        (PaginationUtil.getSortOrders(sortBy))));

        return pageResult;
    }




    @Override
    public List<ConditioningType> getAll() {
        return conditioningTypeRepository.findAll();
    }

    @Override
    public ConditioningType get(Long id) {

        Optional<ConditioningType> foundEntity = conditioningTypeRepository.findById(id);
        if(foundEntity.isPresent()){
            return  foundEntity.get();
        } else {
            throw new EntityNotFoundException("ConditioningType with id "+id+" not found !");
        }
    }

    @Override
    public ConditioningType create(ConditioningType conditioningType) {
        return conditioningTypeRepository.save(conditioningType);
    }

    @Override
    public ConditioningType update(ConditioningType conditioningType) {

        if(get(conditioningType.getId()) != null){
            return conditioningTypeRepository.save(conditioningType);
        } else {
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        conditioningTypeRepository.delete(get(id));
    }
}
