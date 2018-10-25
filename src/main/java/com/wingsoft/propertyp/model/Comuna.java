package com.wingsoft.propertyp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "COMUNA")
public class Comuna {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;


    @Column(name = "NOMBRE")
    private String nombre;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "comuna")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Propiedad> propiedads;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REGION_ID", nullable = false)
    @JsonIgnore
    private Region region;


    public Comuna(){

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

    public List<Propiedad> getPropiedads() {
        return propiedads;
    }

    public void setPropiedads(List<Propiedad> propiedads) {
        this.propiedads = propiedads;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }
}
