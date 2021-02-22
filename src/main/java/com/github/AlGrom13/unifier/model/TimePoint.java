package com.github.AlGrom13.unifier.model;

import java.time.LocalTime;

public class TimePoint {

    private LocalTime value;
    private Service service;
    private boolean isDeparture;
    private int serviceId;

    public TimePoint(LocalTime value, Service service, boolean isDeparture) {
        this.value = value;
        this.service = service;
        this.isDeparture = isDeparture;
    }

    public LocalTime getValue() {
        return value;
    }

    public void setValue(LocalTime value) {
        this.value = value;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public boolean isDeparture() {
        return isDeparture;
    }

    public void setDeparture(boolean departure) {
        isDeparture = departure;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
