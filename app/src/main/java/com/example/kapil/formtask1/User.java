package com.example.kapil.formtask1;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    String first_name,last_name,phone,address,restau_name;

    @SerializedName("title")
    @Expose
    private String requestby;

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRestau_name() {
        return restau_name;
    }

    public void setRestau_name(String restau_name) {
        this.restau_name = restau_name;
    }

    public String getRequestby() {
        return requestby;
    }

    public void setRequestby(String requestby) {
        this.requestby = requestby;
    }

    @Override
    public String toString() {
        return "User{" +
                "requestby='" + requestby + '}';
    }
}
