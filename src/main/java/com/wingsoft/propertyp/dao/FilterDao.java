package com.wingsoft.propertyp.dao;

import com.wingsoft.propertyp.model.Filter;
import com.wingsoft.propertyp.model.Notificacion;
import com.wingsoft.propertyp.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FilterDao extends CrudRepository<Filter, Long> {

    Iterable<Filter> findByUser(User user);
}
