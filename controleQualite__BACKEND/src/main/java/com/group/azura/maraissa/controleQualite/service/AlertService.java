package com.group.azura.maraissa.controleQualite.service;

import com.group.azura.maraissa.controleQualite.entities.postgres.Alert;
import com.group.azura.maraissa.controleQualite.interfaces.service.AlertInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AlertService implements AlertInterface {
    @Override
    public List<Alert> getAll() {
        return null;
    }

    @Override
    public Alert get(Long aLong) {
        return null;
    }

    @Override
    public Alert create(Alert entity) {
        return null;
    }

    @Override
    public Alert update(Alert entity) {
        return null;
    }

    @Override
    public void delete(Long aLong) {

    }
}
