package com.aquamonitor.platform.pc216712u202422549.monitoring.infrastructure.persistence.jpa.assemblers;

import com.aquamonitor.platform.pc216712u202422549.monitoring.domain.model.aggregates.SamplingPlan;
import com.aquamonitor.platform.pc216712u202422549.monitoring.domain.model.valueobjects.SampleVolume;
import com.aquamonitor.platform.pc216712u202422549.monitoring.domain.model.valueobjects.SamplingWindow;
import com.aquamonitor.platform.pc216712u202422549.monitoring.infrastructure.persistence.jpa.embeddables.SampleVolumePersistenceEmbeddable;
import com.aquamonitor.platform.pc216712u202422549.monitoring.infrastructure.persistence.jpa.embeddables.SamplingWindowPersistenceEmbeddable;
import com.aquamonitor.platform.pc216712u202422549.monitoring.infrastructure.persistence.jpa.entities.SamplingPlanPersistenceEntity;

/**
 * Assembler between the sampling plan domain model and its persistence entity.
 *
 * @author Jorge Mateo León Naupari
 */
public final class SamplingPlanPersistenceAssembler {

    private SamplingPlanPersistenceAssembler() {
    }

    public static SamplingPlan toDomainFromPersistence(SamplingPlanPersistenceEntity entity) {
        if (entity == null) return null;
        return SamplingPlan.reconstitute(
                entity.getId(),
                entity.getPlanCode(),
                entity.getStationId(),
                new SamplingWindow(entity.getSamplingWindow().getStartDate(), entity.getSamplingWindow().getEndDate()),
                new SampleVolume(entity.getSampleVolume().getQuantity(), entity.getSampleVolume().getUnit()),
                entity.getPriorityLevel(),
                entity.getStatus(),
                entity.getSamplingNotes());
    }

    public static SamplingPlanPersistenceEntity toPersistenceFromDomain(SamplingPlan samplingPlan) {
        var entity = new SamplingPlanPersistenceEntity();
        entity.setId(samplingPlan.getId());
        entity.setPlanCode(samplingPlan.getPlanCode());
        entity.setStationId(samplingPlan.getStationId());
        entity.setSamplingWindow(new SamplingWindowPersistenceEmbeddable(
                samplingPlan.getSamplingWindow().startDate(), samplingPlan.getSamplingWindow().endDate()));
        entity.setSampleVolume(new SampleVolumePersistenceEmbeddable(
                samplingPlan.getSampleVolume().quantity(), samplingPlan.getSampleVolume().unit()));
        entity.setPriorityLevel(samplingPlan.getPriorityLevel());
        entity.setStatus(samplingPlan.getStatus());
        entity.setSamplingNotes(samplingPlan.getSamplingNotes());
        return entity;
    }
}