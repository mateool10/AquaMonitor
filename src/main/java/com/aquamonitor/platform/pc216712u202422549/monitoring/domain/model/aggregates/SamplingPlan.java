package com.aquamonitor.platform.pc216712u202422549.monitoring.domain.model.aggregates;

import com.aquamonitor.platform.pc216712u202422549.monitoring.domain.model.valueobjects.*;
import com.aquamonitor.platform.pc216712u202422549.shared.domain.model.aggregates.AbstractDomainAggregateRoot;

/**
 * Sampling plan aggregate root.
 *
 * <p>It has no JPA annotations, persistence concerns live in {@code SamplingPlanPersistenceEntity}.</p>
 *
 * @author Jorge Mateo León Naupari
 */
public class SamplingPlan extends AbstractDomainAggregateRoot<SamplingPlan> {

    private static final int MIN_PRIORITY_LEVEL = 1;
    private static final int MAX_PRIORITY_LEVEL = 5;

    private final Long id;
    private final PlanCode planCode;
    private final StationId stationId;
    private final SamplingWindow samplingWindow;
    private final SampleVolume sampleVolume;
    private final Integer priorityLevel;
    private final SamplingPlanStatus status;
    private final String samplingNotes;

    public SamplingPlan(Long id, PlanCode planCode, StationId stationId, SamplingWindow samplingWindow,
                        SampleVolume sampleVolume, Integer priorityLevel, SamplingPlanStatus status,
                        String samplingNotes) {
        if (planCode == null || stationId == null || samplingWindow == null || sampleVolume == null) {
            throw new IllegalArgumentException("Plan code, station id, sampling window and sample volume are required");
        }
        if (priorityLevel == null || priorityLevel < MIN_PRIORITY_LEVEL || priorityLevel > MAX_PRIORITY_LEVEL) {
            throw new IllegalArgumentException("Priority level must be between 1 and 5");
        }
        this.id = id;
        this.planCode = planCode;
        this.stationId = stationId;
        this.samplingWindow = samplingWindow;
        this.sampleVolume = sampleVolume;
        this.priorityLevel = priorityLevel;
        this.status = status != null ? status : SamplingPlanStatus.PENDING;
        this.samplingNotes = samplingNotes;
    }

    public SamplingPlan(PlanCode planCode, StationId stationId, SamplingWindow samplingWindow,
                        SampleVolume sampleVolume, Integer priorityLevel, SamplingPlanStatus status,
                        String samplingNotes) {
        this(null, planCode, stationId, samplingWindow, sampleVolume, priorityLevel, status, samplingNotes);
    }
    
    public static SamplingPlan reconstitute(Long id, PlanCode planCode, StationId stationId,
                                            SamplingWindow samplingWindow, SampleVolume sampleVolume,
                                            Integer priorityLevel, SamplingPlanStatus status, String samplingNotes) {
        return new SamplingPlan(id, planCode, stationId, samplingWindow, sampleVolume, priorityLevel, status,
                samplingNotes);
    }

    public Long getId() {
        return id;
    }

    public PlanCode getPlanCode() {
        return planCode;
    }

    public StationId getStationId() {
        return stationId;
    }

    public SamplingWindow getSamplingWindow() {
        return samplingWindow;
    }

    public SampleVolume getSampleVolume() {
        return sampleVolume;
    }

    public Integer getPriorityLevel() {
        return priorityLevel;
    }

    public SamplingPlanStatus getStatus() {
        return status;
    }

    public String getSamplingNotes() {
        return samplingNotes;
    }
}
