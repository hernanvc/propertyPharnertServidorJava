package com.wingsoft.propertyp.dao;

import com.wingsoft.propertyp.model.Barrios;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BarriosDao extends CrudRepository<Barrios, Long> {


    List<Barrios> findByIsshow(Boolean isshow);

}