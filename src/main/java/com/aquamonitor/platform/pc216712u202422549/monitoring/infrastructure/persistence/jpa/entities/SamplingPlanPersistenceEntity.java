package com.aquamonitor.platform.pc216712u202422549.monitoring.infrastructure.persistence.jpa.entities;

import com.aquamonitor.platform.pc216712u202422549.monitoring.domain.model.valueobjects.PlanCode;
import com.aquamonitor.platform.pc216712u202422549.monitoring.domain.model.valueobjects.SamplingPlanStatus;
import com.aquamonitor.platform.pc216712u202422549.monitoring.domain.model.valueobjects.StationId;
import com.aquamonitor.platform.pc216712u202422549.monitoring.infrastructure.persistence.jpa.converters.PlanCodePersistenceConverter;
import com.aquamonitor.platform.pc216712u202422549.monitoring.infrastructure.persistence.jpa.converters.StationIdPersistenceConverter;
import com.aquamonitor.platform.pc216712u202422549.monitoring.infrastructure.persistence.jpa.embeddables.SampleVolumePersistenceEmbeddable;
import com.aquamonitor.platform.pc216712u202422549.monitoring.infrastructure.persistence.jpa.embeddables.SamplingWindowPersistenceEmbeddable;
import com.aquamonitor.platform.pc216712u202422549.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;
import jakarta.persistence.*;

/**
 * JPA persistence entity for sampling plans.
 *
 * @author Jorge Mateo León Naupari
 */
@Entity
@Table(name = "sampling_plans")
public class SamplingPlanPersistenceEntity extends AuditableAbstractPersistenceEntity {

    @Convert(converter = PlanCodePersistenceConverter.class)
    @Column(nullable = false, unique = true)
    private PlanCode planCode;

    @Convert(converter = StationIdPersistenceConverter.class)
    @Column(nullable = false, length = 36)
    private StationId stationId;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "startDate", column = @Column(name = "sampling_window_start_date", nullable = false)),
            @AttributeOverride(name = "endDate", column = @Column(name = "sampling_window_end_date", nullable = false))})
    private SamplingWindowPersistenceEmbeddable samplingWindow;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "quantity", column = @Column(name = "sample_volume_quantity", nullable = false)),
            @AttributeOverride(name = "unit", column = @Column(name = "sample_volume_unit", nullable = false))})
    private SampleVolumePersistenceEmbeddable sampleVolume;

    @Column(nullable = false)
    private Integer priorityLevel;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private SamplingPlanStatus status;

    private String samplingNotes;

    public SamplingPlanPersistenceEntity() {
    }

    public PlanCode getPlanCode() {
        return planCode;
    }

    public void setPlanCode(PlanCode planCode) {
        this.planCode = planCode;
    }

    public StationId getStationId() {
        return stationId;
    }

    public void setStationId(StationId stationId) {
        this.stationId = stationId;
    }

    public SamplingWindowPersistenceEmbeddable getSamplingWindow() {
        return samplingWindow;
    }

    public void setSamplingWindow(SamplingWindowPersistenceEmbeddable samplingWindow) {
        this.samplingWindow = samplingWindow;
    }

    public SampleVolumePersistenceEmbeddable getSampleVolume() {
        return sampleVolume;
    }

    public void setSampleVolume(SampleVolumePersistenceEmbeddable sampleVolume) {
        this.sampleVolume = sampleVolume;
    }

    public Integer getPriorityLevel() {
        return priorityLevel;
    }

    public void setPriorityLevel(Integer priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    public SamplingPlanStatus getStatus() {
        return status;
    }

    public void setStatus(SamplingPlanStatus status) {
        this.status = status;
    }

    public String getSamplingNotes() {
        return samplingNotes;
    }

    public void setSamplingNotes(String samplingNotes) {
        this.samplingNotes = samplingNotes;
    }
}
