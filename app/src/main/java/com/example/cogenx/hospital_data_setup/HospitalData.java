package com.example.cogenx.hospital_data_setup;

public class HospitalData {
    private String name,address,phoneNumber,state;

    public HospitalData() {
    }

    public HospitalData(String name, String address, String phoneNumber, String state) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
