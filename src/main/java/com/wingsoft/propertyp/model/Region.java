package com.wingsoft.propertyp.model;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "REGION")
public class Region {


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;


    @Column(name = "NOMBRE")
    private String nombre;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "region")
    private List<Comuna> comunas;


    public  Region(){

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

    public List<Comuna> getComunas() {
        return comunas;
    }

    public void setComunas(List<Comuna> comunas) {
        this.comunas = comunas;
    }
}
