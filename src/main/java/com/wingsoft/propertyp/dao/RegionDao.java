package com.wingsoft.propertyp.dao;

import com.wingsoft.propertyp.model.Comuna;
import com.wingsoft.propertyp.model.Region;
import org.springframework.data.repository.CrudRepository;

public interface RegionDao extends CrudRepository<Region, Long> {


}