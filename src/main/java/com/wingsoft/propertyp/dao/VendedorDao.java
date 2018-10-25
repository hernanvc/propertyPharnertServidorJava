package com.wingsoft.propertyp.dao;

import com.wingsoft.propertyp.model.Comuna;
import com.wingsoft.propertyp.model.Vendedor;
import org.springframework.data.repository.CrudRepository;

public interface VendedorDao extends CrudRepository<Vendedor, Long> {


}