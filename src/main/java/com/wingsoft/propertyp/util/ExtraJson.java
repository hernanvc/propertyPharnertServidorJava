package com.wingsoft.propertyp.util;

public class ExtraJson <T>  {
    T extra = null;
    T data = null;


    public ExtraJson(){

    }

    public T getExtra() {
        return extra;
    }

    public void setExtra(T extra) {
        this.extra = extra;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}