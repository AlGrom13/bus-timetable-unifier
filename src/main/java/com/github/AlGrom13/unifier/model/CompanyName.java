package com.github.AlGrom13.unifier.model;

import java.util.Locale;

public enum CompanyName {
    POSH(2),
    GROTTY(1);

    private final int priority;

    private CompanyName(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return this.name().charAt(0) + this.name().substring(1).toLowerCase(Locale.ROOT);
    }
}
