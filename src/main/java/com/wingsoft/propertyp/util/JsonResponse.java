package com.wingsoft.propertyp.util;

import java.util.List;

public class JsonResponse<T>  {
    String mensaje = "";
    boolean error = false;
    List<T> data  = null ;
    long total_propiedades;



    public  JsonResponse(){

    }

    public String getMensaje() {
        return mensaje;
    }
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    public boolean isError() {
        return error;
    }
    public void setError(boolean error) {
        this.error = error;
    }
    public List<T> getData() {
        return data;
    }
    public void setData(List<T> data) {
        this.data = data;
    }

    public long getTotal_propiedades() {
        return total_propiedades;
    }

    public void setTotal_propiedades(long total_propiedades) {
        this.total_propiedades = total_propiedades;
    }
}
