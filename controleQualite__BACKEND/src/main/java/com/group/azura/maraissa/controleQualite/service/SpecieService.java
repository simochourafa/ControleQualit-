package com.group.azura.maraissa.controleQualite.service;

import com.group.azura.maraissa.controleQualite.entities.postgres.ScoringCriteria;
import com.group.azura.maraissa.controleQualite.entities.postgres.Specie;
import com.group.azura.maraissa.controleQualite.interfaces.service.SpecieInterface;
import com.group.azura.maraissa.controleQualite.repository.postgres.ScoringCriteriaRepository;
import com.group.azura.maraissa.controleQualite.repository.postgres.SpecieRepository;
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
public class SpecieService implements SpecieInterface {
    final SpecieRepository specieRepository;
    public Page<Specie> findWithFilters(List<SearchCriteria> criterias, Integer page,
                                                 Integer size,
                                                 String[] sortBy) {
        Specification<Specie> specification = new BaseSpecification<Specie>(criterias);

        Page<Specie> pageResult = specieRepository.findAll(specification,
                PageRequest.of(page - 1, PaginationUtil.checkPaginationPageSize(size),
                        (PaginationUtil.getSortOrders(sortBy))));

        return pageResult;
    }


    @Override
    public List<Specie> getAll() {

        return specieRepository.findAll();
    }

    @Override
    public Specie get(Long id) {

        Optional<Specie> foundEntity = specieRepository.findById(id);
        if(foundEntity.isPresent()){
            return  foundEntity.get();
        } else {
            throw new EntityNotFoundException("scoring criteria with id "+id+" not found !");
        }
    }

    @Override
    public Specie create(Specie specie) {

        return specieRepository.save(specie);
    }

    @Override
    public Specie update(Specie specie) {

        if(get(specie.getId()) != null){
            return specieRepository.save(specie);
        } else {
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        specieRepository.delete(get(id));
    }
}
