package com.wingsoft.propertyp.dao;

import com.wingsoft.propertyp.model.Comuna;
import com.wingsoft.propertyp.model.Region;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RegionDao extends CrudRepository<Region, Long> {

        Iterable<Region> findAll(Pageable pageable);



}