package com.safetynet.safetynetalerts.model.detail;

import java.util.List;

public class Flood {


    private String address;
    private List<Fire> fire;

    public Flood() {

    }

    public Flood(String address, List<Fire> fire) {
        this.address = address;
        this.fire = fire;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Fire> getFire() {
        return fire;
    }

    public void setFire(List<Fire> fire) {
        this.fire = fire;
    }

    @Override
    public String toString() {
        return "Flood{" +
                "address='" + address + '\'' +
                ", fire=" + fire +
                '}';
    }


}
