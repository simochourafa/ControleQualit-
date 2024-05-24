package com.group.azura.maraissa.controleQualite.service;

import com.group.azura.maraissa.controleQualite.entities.postgres.Conditioning;
import com.group.azura.maraissa.controleQualite.entities.postgres.ConditioningType;
import com.group.azura.maraissa.controleQualite.entities.postgres.Defect;
import com.group.azura.maraissa.controleQualite.interfaces.service.DefectInterface;
import com.group.azura.maraissa.controleQualite.repository.postgres.ConditioningTypeRepository;
import com.group.azura.maraissa.controleQualite.repository.postgres.DefectRepository;
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
public class DefectService implements DefectInterface {

    final DefectRepository defectRepository;
    public Page<Defect> findWithFilters(List<SearchCriteria> criterias, Integer page,
                                                  Integer size,
                                                  String[] sortBy) {
        Specification<Defect> specification = new BaseSpecification<Defect>(criterias);

        Page<Defect> pageResult = defectRepository.findAll(specification,
                PageRequest.of(page - 1, PaginationUtil.checkPaginationPageSize(size),
                        (PaginationUtil.getSortOrders(sortBy))));

        return pageResult;
    }




    @Override
    public List<Defect> getAll() {

        return defectRepository.findAll();
    }

    @Override
    public Defect get(Long id) {
        Optional<Defect> foundEntity = defectRepository.findById(id);
        if(foundEntity.isPresent()){
            return  foundEntity.get();
        } else {
            throw new EntityNotFoundException("defect with id "+id+" not found !");
        }
    }

    @Override
    public Defect create(Defect defect) {

        return defectRepository.save(defect);
    }

    @Override
    public Defect update(Defect defect) {

        if(get(defect.getId()) != null){
            return defectRepository.save(defect);
        } else {
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        defectRepository.delete(get(id));
    }
}
