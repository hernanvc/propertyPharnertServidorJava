package com.wingsoft.propertyp.model;


import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "VENDEDOR_DOS")
public class Vendedor {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;


    @Column(name = "EMAIL")
    private String email;

    @Column(name = "NOMBRE")
    private String nombre;


    @Column(name = "TELEFONO")
    private String telefono;


    @Column(name = "imagen")
    private String imagen;

    @Column(name = "nombresucursal")
    private String nombresucursal;

    @Column(name = "CODIGO")
    private Long codigo;




   // @JsonBackReference
    //



    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "vendedors")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    List<Propiedad> propiedades;


    public  Vendedor(){

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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }



    public List<Propiedad> getPropiedades() {
        return propiedades;
    }

    public void setPropiedades(List<Propiedad> propiedades) {
        this.propiedades = propiedades;
    }


    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getNombresucursal() {
        return nombresucursal;
    }

    public void setNombresucursal(String nombresucursal) {
        this.nombresucursal = nombresucursal;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }



}
