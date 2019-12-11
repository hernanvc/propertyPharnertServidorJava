package com.wingsoft.propertyp.dao;

import com.wingsoft.propertyp.model.Notificacion;
import com.wingsoft.propertyp.model.Region;
import com.wingsoft.propertyp.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NotificaionDao extends CrudRepository<Notificacion, Long> {



    List<Notificacion> findByUser(User user);

}