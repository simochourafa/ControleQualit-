package com.group.azura.maraissa.controleQualite.service;

import com.group.azura.maraissa.controleQualite.entities.postgres.AppUser;
import com.group.azura.maraissa.controleQualite.entities.postgres.Conditioning;
import com.group.azura.maraissa.controleQualite.interfaces.service.ConditioningInterface;
import com.group.azura.maraissa.controleQualite.repository.postgres.AppUserRepository;
import com.group.azura.maraissa.controleQualite.repository.postgres.ConditioningRepository;
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
public class ConditioningService implements ConditioningInterface {
    final ConditioningRepository conditioningRepository;
    public Page<Conditioning> findWithFilters(List<SearchCriteria> criterias, Integer page,
                                         Integer size,
                                         String[] sortBy) {
        Specification<Conditioning> specification = new BaseSpecification<Conditioning>(criterias);

        Page<Conditioning> pageResult = conditioningRepository.findAll(specification,
                PageRequest.of(page - 1, PaginationUtil.checkPaginationPageSize(size),
                        (PaginationUtil.getSortOrders(sortBy))));

        return pageResult;
    }




    @Override
    public List<Conditioning> getAll() {
        return conditioningRepository.findAll();
    }

    @Override
    public Conditioning get(Long id) {
        Optional<Conditioning> foundEntity = conditioningRepository.findById(id);
        if(foundEntity.isPresent()){
            return  foundEntity.get();
        } else {
            throw new EntityNotFoundException("Conditioning with id "+id+" not found !");
        }
    }

    @Override
    public Conditioning create(Conditioning conditioning) {

        return  conditioningRepository.save(conditioning);
    }

    @Override
    public Conditioning update(Conditioning conditioning) {

        if(get(conditioning.getId()) != null){
            return conditioningRepository.save(conditioning);
        } else {
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        conditioningRepository.delete(get(id));
    }
}
