package com.wingsoft.propertyp.model;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "NOTIFICACIONES")
public class Notificacion {


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "MENSAJE")
    private String mensaje;

    @Column(name = "ESTADO")
    private boolean estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User user;


    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "notificacions")
    List<Propiedad> propiedads;




    public Notificacion(){

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Propiedad> getPropiedads() {
        return propiedads;
    }

    public void setPropiedads(List<Propiedad> propiedads) {
        this.propiedads = propiedads;
    }



}
