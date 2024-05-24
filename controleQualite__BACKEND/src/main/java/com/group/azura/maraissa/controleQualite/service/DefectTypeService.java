package com.group.azura.maraissa.controleQualite.service;

import com.group.azura.maraissa.controleQualite.entities.postgres.Defect;
import com.group.azura.maraissa.controleQualite.entities.postgres.DefectType;
import com.group.azura.maraissa.controleQualite.interfaces.service.DefectTypeInterface;
import com.group.azura.maraissa.controleQualite.repository.postgres.DefectRepository;
import com.group.azura.maraissa.controleQualite.repository.postgres.DefectTypeRepository;
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
public class DefectTypeService implements DefectTypeInterface {
    final DefectTypeRepository defectTypeRepository;
    public Page<DefectType> findWithFilters(List<SearchCriteria> criterias, Integer page,
                                        Integer size,
                                        String[] sortBy) {
        Specification<DefectType> specification = new BaseSpecification<DefectType>(criterias);

        Page<DefectType> pageResult = defectTypeRepository.findAll(specification,
                PageRequest.of(page - 1, PaginationUtil.checkPaginationPageSize(size),
                        (PaginationUtil.getSortOrders(sortBy))));

        return pageResult;
    }

    @Override
    public List<DefectType> getAll() {
        return defectTypeRepository.findAll();
    }

    @Override
    public DefectType get(Long id) {
        Optional<DefectType> foundEntity = defectTypeRepository.findById(id);
        if(foundEntity.isPresent()){
            return  foundEntity.get();
        } else {
            throw new EntityNotFoundException("defect type with id "+id+" not found !");
        }
    }

    @Override
    public DefectType create(DefectType defectType) {
        return defectTypeRepository.save(defectType);
    }

    @Override
    public DefectType update(DefectType defectType) {
        if(get(defectType.getId()) != null){
            return defectTypeRepository.save(defectType);
        } else {
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        defectTypeRepository.delete(get(id));
    }
}
