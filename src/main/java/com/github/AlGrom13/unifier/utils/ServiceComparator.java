package com.github.AlGrom13.unifier.utils;

import com.github.AlGrom13.unifier.model.Service;

import java.util.Comparator;

public class ServiceComparator implements Comparator<Service> {

    @Override
    public int compare(Service o1, Service o2) {
        return o2.getCompanyName().getPriority() - o1.getCompanyName().getPriority();
    }
}
