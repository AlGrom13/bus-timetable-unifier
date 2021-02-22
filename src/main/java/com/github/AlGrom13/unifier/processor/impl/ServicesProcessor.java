package com.github.AlGrom13.unifier.processor.impl;

import com.github.AlGrom13.unifier.model.Service;
import com.github.AlGrom13.unifier.model.TimePoint;
import com.github.AlGrom13.unifier.processor.Processor;
import com.github.AlGrom13.unifier.utils.ServiceComparator;
import com.github.AlGrom13.unifier.utils.TimePointComparator;

import java.util.*;

public class ServicesProcessor extends CommonProcessor {

    public ServicesProcessor(Processor next) {
        super(next, Service[].class);
    }

    @Override
    protected void processData(Object data) {
        Service[] services = (Service[]) data;
        TimePoint[] timePoints = collectTimePoints(services);
        Arrays.sort(timePoints, new TimePointComparator());
        Service[] efficientServices = findEfficientServices(services, timePoints);
        Arrays.sort(efficientServices, new ServiceComparator());
        next.process(efficientServices);
    }

    private TimePoint[] collectTimePoints(Service[] services) {
        TimePoint[] timePoints = new TimePoint[services.length * 2];
        for (int i = 0; i < services.length; i++) {
            services[i].getDeparture().setServiceId(i);
            services[i].getArrival().setServiceId(i);
            timePoints[2*i] = services[i].getDeparture();
            timePoints[2*i+1] = services[i].getArrival();
        }
        return timePoints;
    }

    private Service[] findEfficientServices(Service[] services, TimePoint[] sortedTimePoints) {
        List<Service> efficientServices = new LinkedList<>();
        LinkedList<TimePoint> pointDeque = new LinkedList<>();
        boolean[] servicesProcessed = new boolean[services.length];
        int serviceId;
        boolean isServiceProcessed;
        for (TimePoint point: sortedTimePoints) {
            serviceId = point.getServiceId();
            isServiceProcessed = servicesProcessed[serviceId];
            if (isServiceProcessed) {
                continue;
            }
            if (point.isDeparture()) {
                pointDeque.offerFirst(point);
                continue;
            }
            TimePoint endPoint = pointDeque.pollLast();
            boolean isNotPairPoints = serviceId != endPoint.getServiceId();
            while (isNotPairPoints) {
                servicesProcessed[endPoint.getServiceId()] = true;
                endPoint = pointDeque.pollLast();
                isNotPairPoints = serviceId != endPoint.getServiceId();
            }
            servicesProcessed[serviceId] = true;
            efficientServices.add(services[serviceId]);
        }
        return efficientServices.toArray(Service[]::new);
    }

}
