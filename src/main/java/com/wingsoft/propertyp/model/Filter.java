package com.wingsoft.propertyp.model;

import javax.persistence.*;


@Entity
@Table(name = "Filter")
public class Filter {


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "description")
    String description ;

    @Column(name = "tipo")
    String tipo ;

    @Column(name = "objetivo")
    String objetivo ;

    @Column(name = "comuna")
    long comuna ;

    @Column(name = "max")
    double max ;

    @Column(name = "min")
    double min;

    @Column(name = "unidad")
    String unidad ;

    @Column(name = "region")
    long region ;




    public Filter(){

    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }


    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getComuna() {
        return comuna;
    }

    public void setComuna(long comuna) {
        this.comuna = comuna;
    }

    public long getRegion() {
        return region;
    }

    public void setRegion(long region) {
        this.region = region;
    }
}
