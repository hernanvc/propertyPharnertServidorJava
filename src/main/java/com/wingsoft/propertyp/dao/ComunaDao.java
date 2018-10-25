package com.wingsoft.propertyp.dao;

import com.wingsoft.propertyp.model.Comuna;
import com.wingsoft.propertyp.model.Region;
import com.wingsoft.propertyp.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ComunaDao extends CrudRepository<Comuna, Long> {

        List<Comuna> findByRegion(Region region);
        Comuna findByNombre(String nombre);



}