package com.group.azura.maraissa.controleQualite.interfaces;

import org.springframework.data.domain.Page;

import java.util.List;

public interface CrudInterface<T , ID>{

    List<T> getAll();
    T get(ID id);
    T create(T entity) ;
    T update(T entity);
    void delete(ID id);
}
