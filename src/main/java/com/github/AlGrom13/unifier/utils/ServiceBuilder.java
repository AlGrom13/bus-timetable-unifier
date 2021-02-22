package com.github.AlGrom13.unifier.utils;

import com.github.AlGrom13.unifier.model.CompanyName;
import com.github.AlGrom13.unifier.model.Service;
import com.github.AlGrom13.unifier.model.TimePoint;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class ServiceBuilder {

    private final int REQUIRED_PARAMS_NUMBER = 3;
    private final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    private ServiceBuilder() {

    }

    private static class ServiceBuilderHolder {
        private final static ServiceBuilder INSTANCE = new ServiceBuilder();
    }

    public static ServiceBuilder getInstance() {
        return ServiceBuilderHolder.INSTANCE;
    }

    public Service build(String[] params) {
        if (params.length != REQUIRED_PARAMS_NUMBER) {
            return null;
        }
        CompanyName companyName = parseCompanyName(params[0]);
        LocalTime departureTime = parseTime(params[1]);
        LocalTime arrivalTime = parseTime(params[2]);
        boolean isParamsParsedSuccessful = companyName != null && departureTime != null && arrivalTime != null;
        if (isParamsParsedSuccessful) {
            Duration duration = Duration.between(departureTime, arrivalTime);
            Service service = new Service();
            service.setCompanyName(companyName);
            service.setDeparture(new TimePoint(departureTime, service, true));
            service.setArrival(new TimePoint(arrivalTime, service, false));
            service.setDuration(duration);
            return service;
        } else {
            return null;
        }
    }

    private CompanyName parseCompanyName(String param) {
        try {
            return CompanyName.valueOf(param.toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    private LocalTime parseTime(String param) {
        try {
            return LocalTime.parse(param, TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

}
