package com.wingsoft.propertyp.model;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "EMAIL")
    //@Size(max = 20, min = 3, message = "{user.name.invalid}")
    private String email;


    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "CLAVE")
    //@Size(max = 20, min = 3, message = "{user.name.invalid}")
    private String clave;


    @Column(name = "NOMBRE")
    //@Size(max = 20, min = 3, message = "{user.name.invalid}")
    private String nombre;




    @Column(name = "TOKEN")
    //@Size(max = 20, min = 3, message = "{user.name.invalid}")
    private String token;

    @Column(name = "DEVICE_TOKEN")
    //@Size(max = 20, min = 3, message = "{user.name.invalid}")
    private String device_token;


    @Column(name = "DEVICE")
    //@Size(max = 20, min = 3, message = "{user.name.invalid}")
    private String device;


    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "users")
    List<Propiedad> propiedads;

    @Column(name = "LOGINTYPE")
    //@Size(max = 20, min = 3, message = "{user.name.invalid}")
    private String tipo;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Notificacion> notificacions;


    public  User (){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDevice_token() {
        return device_token;
    }

    public void setDevice_token(String device_token) {
        this.device_token = device_token;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public List<Propiedad> getPropiedads() {
        return propiedads;
    }

    public void setPropiedads(List<Propiedad> propiedads) {
        this.propiedads = propiedads;
    }


    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<Notificacion> getNotificacions() {
        return notificacions;
    }

    public void setNotificacions(List<Notificacion> notificacions) {
        this.notificacions = notificacions;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
