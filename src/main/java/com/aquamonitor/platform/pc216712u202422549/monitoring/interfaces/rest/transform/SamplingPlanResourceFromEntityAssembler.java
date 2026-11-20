package com.aquamonitor.platform.pc216712u202422549.monitoring.interfaces.rest.transform;

import com.aquamonitor.platform.pc216712u202422549.monitoring.domain.model.aggregates.SamplingPlan;
import com.aquamonitor.platform.pc216712u202422549.monitoring.interfaces.rest.resources.SamplingPlanResource;

/**
 * Assembler to convert a SamplingPlan aggregate into a SamplingPlanResource.
 *
 * @author Jorge Mateo León Naupari
 */
public class SamplingPlanResourceFromEntityAssembler {
    
    public static SamplingPlanResource toResourceFromEntity(SamplingPlan entity) {
        return new SamplingPlanResource(
                entity.getId(),
                entity.getPlanCode().code(),
                entity.getStationId().value().toString(),
                entity.getSamplingWindow().startDate().toString(),
                entity.getSamplingWindow().endDate().toString(),
                entity.getSampleVolume().quantity(),
                entity.getSampleVolume().unit(),
                entity.getPriorityLevel(),
                entity.getStatus().name(),
                entity.getSamplingNotes());
    }
}
