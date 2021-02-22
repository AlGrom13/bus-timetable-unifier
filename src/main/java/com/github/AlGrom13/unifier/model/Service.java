package com.github.AlGrom13.unifier.model;

import java.time.Duration;

public class Service {

    private CompanyName companyName;
    private TimePoint departure;
    private TimePoint arrival;
    private Duration duration;

    public CompanyName getCompanyName() {
        return companyName;
    }

    public void setCompanyName(CompanyName companyName) {
        this.companyName = companyName;
    }

    public TimePoint getDeparture() {
        return departure;
    }

    public void setDeparture(TimePoint departure) {
        this.departure = departure;
    }

    public TimePoint getArrival() {
        return arrival;
    }

    public void setArrival(TimePoint arrival) {
        this.arrival = arrival;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s", companyName, departure, arrival);
    }
}
