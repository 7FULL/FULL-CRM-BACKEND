package com.example.crm_backend.network;

/**
 *
 *@author Pablo Hermida Gómez DAM G2
 *
 */

/**
 * This class is used to send a response to the client. It contains a code and a data object
 */
public class Data {

    /**
     * Code of the response. I've used standard HTTP codes
     */
    private int code;

    /**
     * Data of the response. It can be any object
     */
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
