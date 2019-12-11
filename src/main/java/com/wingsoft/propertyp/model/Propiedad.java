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

    @Column(name = "CODIGO_final")
    private String codigo_final;

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


    @Column(name = "IMANGEN_6")
    private String imagen6;

    @Column(name = "IMANGEN_7")
    private String imagen7;


    @Column(name = "IMANGEN_8")
    private String imagen8;


    @Column(name = "IMANGEN_9")
    private String imagen9;

    @Column(name = "IMANGEN_10")
    private String imagen10;

    @Column(name = "IMANGEN_11")
    private String imagen11;

    @Column(name = "IMANGEN_12")
    private String imagen12;

    @Column(name = "IMANGEN_13")
    private String imagen13;

    @Column(name = "IMANGEN_14")
    private String imagen14;

    @Column(name = "IMANGEN_15")
    private String imagen15;

    @Column(name = "IMANGEN_16")
    private String imagen16;

    @Column(name = "IMANGEN_17")
    private String imagen17;

    @Column(name = "IMANGEN_18")
    private String imagen18;

    @Column(name = "IMANGEN_19")
    private String imagen19;

    @Column(name = "IMANGEN_20")
    private String imagen20;

    @Column(name = "IMANGEN_21")
    private String imagen21;

    @Column(name = "IMANGEN_22")
    private String imagen22;


    @Column(name = "IMANGEN_23")
    private String imagen23;


    @Column(name = "IMANGEN_24")
    private String imagen24;

    @Column(name = "IMANGEN_25")
    private String imagen25;

    @Column(name = "IMANGEN_26")
    private String imagen26;

    @Column(name = "IMANGEN_27")
    private String imagen27;

    @Column(name = "IMANGEN_28")
    private String imagen28;

    @Column(name = "IMANGEN_29")
    private String imagen29;

    @Column(name = "IMANGEN_30")
    private String imagen30;

    @Column(name = "IMANGEN_31")
    private String imagen31;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "IMANGEN_32")
    private String imagen32;


    @Column(name = "Barrio")
    private String barrio;

    @Column(name = "disponible")
    private int disponible;


    @Column(name = "Etiqueta")
    private String etiqueta;





    @Column(name = "ISFAVORITO")
    private  boolean isfavorito = false;


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

    public boolean isIsfavorito() {
        return isfavorito;
    }

    public void setIsfavorito(boolean isfavorito) {
        this.isfavorito = isfavorito;
    }

    public int getDisponible() {
        return disponible;
    }

    public void setDisponible(int disponible) {
        this.disponible = disponible;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }


    public String getImagen6() {
        return imagen6;
    }

    public void setImagen6(String imagen6) {
        this.imagen6 = imagen6;
    }

    public String getImagen7() {
        return imagen7;
    }

    public void setImagen7(String imagen7) {
        this.imagen7 = imagen7;
    }

    public String getImagen8() {
        return imagen8;
    }

    public void setImagen8(String imagen8) {
        this.imagen8 = imagen8;
    }

    public String getImagen9() {
        return imagen9;
    }

    public void setImagen9(String imagen9) {
        this.imagen9 = imagen9;
    }

    public String getImagen10() {
        return imagen10;
    }

    public void setImagen10(String imagen10) {
        this.imagen10 = imagen10;
    }

    public String getImagen11() {
        return imagen11;
    }

    public void setImagen11(String imagen11) {
        this.imagen11 = imagen11;
    }

    public String getImagen12() {
        return imagen12;
    }

    public void setImagen12(String imagen12) {
        this.imagen12 = imagen12;
    }

    public String getImagen13() {
        return imagen13;
    }

    public void setImagen13(String imagen13) {
        this.imagen13 = imagen13;
    }

    public String getImagen14() {
        return imagen14;
    }

    public void setImagen14(String imagen14) {
        this.imagen14 = imagen14;
    }

    public String getImagen15() {
        return imagen15;
    }

    public void setImagen15(String imagen15) {
        this.imagen15 = imagen15;
    }

    public String getImagen16() {
        return imagen16;
    }

    public void setImagen16(String imagen16) {
        this.imagen16 = imagen16;
    }

    public String getImagen17() {
        return imagen17;
    }

    public void setImagen17(String imagen17) {
        this.imagen17 = imagen17;
    }

    public String getImagen18() {
        return imagen18;
    }

    public void setImagen18(String imagen18) {
        this.imagen18 = imagen18;
    }

    public String getImagen19() {
        return imagen19;
    }

    public void setImagen19(String imagen19) {
        this.imagen19 = imagen19;
    }

    public String getImagen21() {
        return imagen21;
    }

    public void setImagen21(String imagen21) {
        this.imagen21 = imagen21;
    }

    public String getImagen22() {
        return imagen22;
    }

    public void setImagen22(String imagen22) {
        this.imagen22 = imagen22;
    }

    public String getImagen23() {
        return imagen23;
    }

    public void setImagen23(String imagen23) {
        this.imagen23 = imagen23;
    }

    public String getImagen24() {
        return imagen24;
    }

    public void setImagen24(String imagen24) {
        this.imagen24 = imagen24;
    }

    public String getImagen25() {
        return imagen25;
    }

    public void setImagen25(String imagen25) {
        this.imagen25 = imagen25;
    }

    public String getImagen26() {
        return imagen26;
    }

    public void setImagen26(String imagen26) {
        this.imagen26 = imagen26;
    }

    public String getImagen27() {
        return imagen27;
    }

    public void setImagen27(String imagen27) {
        this.imagen27 = imagen27;
    }

    public String getImagen28() {
        return imagen28;
    }

    public void setImagen28(String imagen28) {
        this.imagen28 = imagen28;
    }

    public String getImagen29() {
        return imagen29;
    }

    public void setImagen29(String imagen29) {
        this.imagen29 = imagen29;
    }

    public String getImagen30() {
        return imagen30;
    }

    public void setImagen30(String imagen30) {
        this.imagen30 = imagen30;
    }

    public String getImagen31() {
        return imagen31;
    }

    public void setImagen31(String imagen31) {
        this.imagen31 = imagen31;
    }

    public String getImagen32() {
        return imagen32;
    }

    public void setImagen32(String imagen32) {
        this.imagen32 = imagen32;
    }


    public String getImagen20() {
        return imagen20;
    }

    public void setImagen20(String imagen20) {
        this.imagen20 = imagen20;
    }

    public String getCodigo_final() {
        return codigo_final;
    }

    public void setCodigo_final(String codigo_final) {
        this.codigo_final = codigo_final;
    }
}
