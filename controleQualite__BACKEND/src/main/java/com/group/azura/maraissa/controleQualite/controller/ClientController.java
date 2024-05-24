package com.group.azura.maraissa.controleQualite.controller;


import com.group.azura.maraissa.controleQualite.entities.postgres.Client;
import com.group.azura.maraissa.controleQualite.entities.postgres.Specie;
import com.group.azura.maraissa.controleQualite.interfaces.service.ClientInterface;
import com.group.azura.maraissa.controleQualite.service.AttackLevelService;
import com.group.azura.maraissa.controleQualite.service.ClientService;
import com.group.azura.maraissa.controleQualite.specification.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/client")
@RequiredArgsConstructor
public class ClientController implements ClientInterface {
    final ClientService clientService;
    @PostMapping("/find-with-filters")
    public ResponseEntity<Page<Client>> findWithFilters(@RequestBody  List<SearchCriteria> criterias,

                                                        @RequestParam(defaultValue = "1") Integer page,
                                                        @RequestParam(defaultValue = "9") Integer size,
                                                        @RequestParam(value = "sort[]", required = false) String[] sort) {
        return new ResponseEntity<Page<Client>>(clientService.findWithFilters(
                criterias, page, size, sort), HttpStatus.OK);
    }


    @GetMapping
    @Override
    public List<Client> getAll() {
        return clientService.getAll();
    }

    @GetMapping("/{id}")
    @Override
    public Client get(@PathVariable Long id) {
        return clientService.get(id);
    }

    @PostMapping
    @Override
    public Client create(@RequestBody Client client) {
        return clientService.create(client);
    }//201

    @PutMapping
    @Override
    public Client update(@RequestBody Client client) {
        return clientService.update(client);
    }


    @DeleteMapping("/{id}")
    @Override
    public void delete(Long id) {
        clientService.delete(id);
    }
}
