package com.example.crm_backend.network;

/*
 *
 *@author Pablo Hermida Gómez DAM G2
 *
 */
public class Data {

    private int code;

    private Object data;

    public Data(int code, Object data){
        this.code = code;
        this.data = data;
    }

    //region getters and setters
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    //endregion

    @Override
    public String toString() {
        return "Data{" +
                "code=" + code +
                ", data=" + data +
                '}';
    }
}
