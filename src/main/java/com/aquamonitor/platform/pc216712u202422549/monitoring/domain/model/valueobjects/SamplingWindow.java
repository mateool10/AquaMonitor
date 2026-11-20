package com.aquamonitor.platform.pc216712u202422549.monitoring.domain.model.valueobjects;

import java.time.LocalDate;

/**
 * Value object that represents the period in which the water samples must be collected.
 *
 * @param startDate the start date of the window
 * @param endDate   the end date of the window, it must be after the start date
 * @author Jorge Mateo León Naupari
 */
public record SamplingWindow(LocalDate startDate, LocalDate endDate) {

    public SamplingWindow {
        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException("Sampling window start date and end date must not be null");
        }
        if (!endDate.isAfter(startDate)) {
            throw new IllegalArgumentException("Sampling window end date must be after the start date");
        }
    }

    public boolean contains(LocalDate checkDate) {
        if (checkDate == null) {
            throw new IllegalArgumentException("Check date must not be null");
        }
        return !checkDate.isBefore(startDate) && !checkDate.isAfter(endDate);
    }
}
