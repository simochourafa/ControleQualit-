package com.group.azura.maraissa.controleQualite.service;

import com.group.azura.maraissa.controleQualite.entities.postgres.AttackLevel;
import com.group.azura.maraissa.controleQualite.entities.postgres.Client;
import com.group.azura.maraissa.controleQualite.entities.postgres.Specie;
import com.group.azura.maraissa.controleQualite.interfaces.service.ClientInterface;
import com.group.azura.maraissa.controleQualite.repository.postgres.AttackLevelRepository;
import com.group.azura.maraissa.controleQualite.repository.postgres.ClientRepository;
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
public class ClientService implements ClientInterface {

    final ClientRepository clientRepository;
    public Page<Client> findWithFilters(List<SearchCriteria> criterias, Integer page,
                                        Integer size,
                                        String[] sortBy) {
        Specification<Client> specification = new BaseSpecification<Client>(criterias);

        Page<Client> pageResult = clientRepository.findAll(specification,
                PageRequest.of(page - 1, PaginationUtil.checkPaginationPageSize(size),
                        (PaginationUtil.getSortOrders(sortBy))));

        return pageResult;
    }


    @Override
    public List<Client> getAll() {

        return clientRepository.findAll();
    }

    @Override
    public Client get(Long id) {

        Optional<Client> foundEntity = clientRepository.findById(id);
        if(foundEntity.isPresent()){
            return  foundEntity.get();
        } else {
            throw new EntityNotFoundException("client with id "+id+" not found !");
        }
    }

    @Override
    public Client create(Client client) {

        return  clientRepository.save(client);
    }

    @Override
    public Client update(Client client) {

        if(get(client.getId()) != null){
            return clientRepository.save(client);
        } else {
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        clientRepository.delete(get(id));
    }
}
