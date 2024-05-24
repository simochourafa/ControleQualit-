package com.group.azura.maraissa.controleQualite.service;

import com.group.azura.maraissa.controleQualite.entities.postgres.DefectType;
import com.group.azura.maraissa.controleQualite.entities.postgres.Frequency;
import com.group.azura.maraissa.controleQualite.interfaces.service.FrequencyInterface;
import com.group.azura.maraissa.controleQualite.repository.postgres.DefectTypeRepository;
import com.group.azura.maraissa.controleQualite.repository.postgres.FrequencyRepository;
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
public class FrequencyService implements FrequencyInterface {
    final FrequencyRepository frequencyRepository;
    public Page<Frequency> findWithFilters(List<SearchCriteria> criterias, Integer page,
                                            Integer size,
                                            String[] sortBy) {
        Specification<Frequency> specification = new BaseSpecification<Frequency>(criterias);

        Page<Frequency> pageResult = frequencyRepository.findAll(specification,
                PageRequest.of(page - 1, PaginationUtil.checkPaginationPageSize(size),
                        (PaginationUtil.getSortOrders(sortBy))));

        return pageResult;
    }


    @Override
    public List<Frequency> getAll() {

        return frequencyRepository.findAll();
    }

    @Override
    public Frequency get(Long id) {

        Optional<Frequency> foundEntity = frequencyRepository.findById(id);
        if(foundEntity.isPresent()){
            return  foundEntity.get();
        } else {
            throw new EntityNotFoundException("frequency with id "+id+" not found !");
        }
    }

    @Override
    public Frequency create(Frequency frequency) {

        return frequencyRepository.save(frequency);
    }

    @Override
    public Frequency update(Frequency frequency) {

        if(get(frequency.getId()) != null){
            return frequencyRepository.save(frequency);
        } else {
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        frequencyRepository.delete(get(id));
    }
}
