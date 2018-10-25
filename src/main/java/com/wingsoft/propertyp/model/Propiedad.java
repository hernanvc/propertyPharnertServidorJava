package com.wingsoft.propertyp.model;


import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Propiedad")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Propiedad {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "CODIGO")
    private long codigo;

    @Column(name = "TIPO")
    private String tipo;

    @Column(name = "OBJETIVO")
    private String objetivo;

    @Column(name = "DIRECCION")
    private String direccion;

    @Column(name = "TIPODEMONEDAS")
    private String tipoDeMoneda;

    @Column(name = "METROSINTERNOS")
    private double metrosInterno;

    @Column(name = "METROSTERRENO")
    private double metrosTerrono;

    @Column(name = "DORMITORIOS")
    private int dormitorios;

    @Column(name = "BANOS")
    private int banos;

    @Column(name = "VALOR")
    private double valor;

    @Column(name = "AMOBLADO" )
    private boolean amoblado;

    @Column(name = "DESCRIPCION",columnDefinition="TEXT")
    private String description;

    @Column(name = "IMANGEN_1")
    private String imagenUno;

    @Column(name = "IMANGEN_2")
    private String imagenDos;

    @Column(name = "IMANGEN_3")
    private String imagenTres;

    @Column(name = "IMANGEN_4")
    private String imagenCuatro;

    @Column(name = "IMANGEN_5")
    private String imagenCinco;


    @Column(name = "Barrio")
    private String barrio;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMUNA_ID", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Comuna comuna;



    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "vendedor_property", joinColumns = {
            @JoinColumn(name = "PROPERTY_ID", nullable = true, updatable = true) },
            inverseJoinColumns = { @JoinColumn(name = "VENDEDOR_ID",
                    nullable = false, updatable = false) })
    private List<Vendedor> vendedors ;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinTable(name = "user_property", joinColumns = {
            @JoinColumn(name = "PROPERTY_ID", nullable = true, updatable = true) },
            inverseJoinColumns = { @JoinColumn(name = "USER_ID",
                    nullable = false, updatable = false) })
    List<User> users;



    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinTable(name = "notifica_property", joinColumns = {
            @JoinColumn(name = "PROPERTY_ID", nullable = true, updatable = true) },
            inverseJoinColumns = { @JoinColumn(name = "NOTIFICATION_ID",
                    nullable = false, updatable = false) })
    List<Notificacion> notificacions;



    public  Propiedad(){

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public String getTipoDeMoneda() {
        return tipoDeMoneda;
    }

    public void setTipoDeMoneda(String tipoDeMoneda) {
        this.tipoDeMoneda = tipoDeMoneda;
    }

    public double getMetrosInterno() {
        return metrosInterno;
    }

    public void setMetrosInterno(double metrosInterno) {
        this.metrosInterno = metrosInterno;
    }

    public double getMetrosTerrono() {
        return metrosTerrono;
    }

    public void setMetrosTerrono(double metrosTerrono) {
        this.metrosTerrono = metrosTerrono;
    }

    public int getDormitorios() {
        return dormitorios;
    }

    public void setDormitorios(int dormitorios) {
        this.dormitorios = dormitorios;
    }

    public int getBanos() {
        return banos;
    }

    public void setBanos(int banos) {
        this.banos = banos;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public boolean isAmoblado() {
        return amoblado;
    }

    public void setAmoblado(boolean amoblado) {
        this.amoblado = amoblado;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagenUno() {
        return imagenUno;
    }

    public void setImagenUno(String imagenUno) {
        this.imagenUno = imagenUno;
    }

    public String getImagenDos() {
        return imagenDos;
    }

    public void setImagenDos(String imagenDos) {
        this.imagenDos = imagenDos;
    }

    public String getImagenTres() {
        return imagenTres;
    }

    public void setImagenTres(String imagenTres) {
        this.imagenTres = imagenTres;
    }

    public String getImagenCuatro() {
        return imagenCuatro;
    }

    public void setImagenCuatro(String imagenCuatro) {
        this.imagenCuatro = imagenCuatro;
    }

    public String getImagenCinco() {
        return imagenCinco;
    }

    public void setImagenCinco(String imagenCinco) {
        this.imagenCinco = imagenCinco;
    }

    public Comuna getComuna() {
        return comuna;
    }

    public void setComuna(Comuna comuna) {
        this.comuna = comuna;
    }



    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }


    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Notificacion> getNotificacions() {
        return notificacions;
    }

    public void setNotificacions(List<Notificacion> notificacions) {
        this.notificacions = notificacions;
    }


    public List<Vendedor> getVendedors() {
        return vendedors;
    }

    public void setVendedors(List<Vendedor> vendedors) {
        this.vendedors = vendedors;
    }


    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }
}
