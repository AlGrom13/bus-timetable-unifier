package com.github.AlGrom13.unifier.utils;

import com.github.AlGrom13.unifier.model.TimePoint;

import java.util.Comparator;

public class TimePointComparator implements Comparator<TimePoint> {

    @Override
    public int compare(TimePoint o1, TimePoint o2) {
        if (!o1.getValue().equals(o2.getValue())) {
            return o1.getValue().compareTo(o2.getValue());
        }
        boolean isDifferentPartOfService = o1.isDeparture() ^ o2.isDeparture();
        if (isDifferentPartOfService) {
            return o1.isDeparture() ? -1 : 1;
        }
        int compareDurationRes = o1.getService().getDuration().compareTo(o2.getService().getDuration());
        if (compareDurationRes == 0) {
            return o1.isDeparture() ?
                    o1.getService().getCompanyName().getPriority() - o2.getService().getCompanyName().getPriority() :
                    o2.getService().getCompanyName().getPriority() - o1.getService().getCompanyName().getPriority();
        }
        return compareDurationRes;
    }
}
