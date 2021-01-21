package com.cenlly.domain;

public class DataModelBase {
    private String code;
    private Object data;
    private String message;
    private String tti;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTti() {
        return tti;
    }

    public void setTti(String tti) {
        this.tti = tti;
    }

    @Override
    public String toString() {
        return "DataModelBase{" +
                "code='" + code + '\'' +
                ", data=" + data +
                ", message='" + message + '\'' +
                ", tti='" + tti + '\'' +
                '}';
    }
}
