package com.wingsoft.propertyp.model;

import javax.persistence.*;


@Entity
@Table(name = "BARRIOS")
public class Barrios {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;


    @Column(name = "NOMBRE")
    private String nombre;


    @Column(name = "imagen")
    private String imagen;

    @Column(name = "ISSHOW")
    private boolean isshow;


    public Barrios(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public boolean isIsshow() {
        return isshow;
    }

    public void setIsshow(boolean isshow) {
        this.isshow = isshow;
    }


}

