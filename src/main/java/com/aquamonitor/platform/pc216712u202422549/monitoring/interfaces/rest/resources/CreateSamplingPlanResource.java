package com.aquamonitor.platform.pc216712u202422549.monitoring.interfaces.rest.resources;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

/**
 * Resource with the data to create a sampling plan.
 *
 * <p>It is a class with setters instead of a record so it can tell when {@code status}
 * was not sent and when it was sent with a null value.</p>
 *
 * @author Jorge Mateo León Naupari
 */
@Schema(name = "CreateSamplingPlanRequest", description = "Request payload to create a new sampling plan")
public class CreateSamplingPlanResource {

    @NotBlank(message = "{validation.not-blank}")
    @Schema(description = "Unique code of the sampling plan", example = "SAM-001")
    private String planCode;

    @NotBlank(message = "{validation.not-blank}")
    @Pattern(regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$", message = "{validation.uuid}")
    @Schema(description = "Monitoring station identifier", example = "550e8400-e29b-41d4-a716-446655440000")
    private String stationId;

    @NotBlank(message = "{validation.not-blank}")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "{validation.date}")
    @Schema(description = "Sampling window start date (yyyy-MM-dd)", example = "2026-11-21")
    private String samplingWindowStartDate;

    @NotBlank(message = "{validation.not-blank}")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "{validation.date}")
    @Schema(description = "Sampling window end date (yyyy-MM-dd)", example = "2026-11-25")
    private String samplingWindowEndDate;

    @NotNull(message = "{validation.not-null}")
    @Schema(description = "Volume of water to collect", example = "10")
    private Integer sampleVolumeQuantity;

    @NotBlank(message = "{validation.not-blank}")
    @Schema(description = "Unit of the sample volume", example = "LITER")
    private String sampleVolumeUnit;

    @NotNull(message = "{validation.not-null}")
    @Schema(description = "Priority level from 1 (lowest) to 5 (highest)", example = "3")
    private Integer priorityLevel;

    @Pattern(regexp = "^(PENDING|APPROVED|COMPLETED|CANCELLED)$", message = "{validation.status}")
    @Schema(description = "Status of the plan, PENDING when it is not sent", example = "PENDING")
    private String status;

    @JsonIgnore
    private boolean statusProvided;

    @Schema(description = "Optional notes of the sampling plan", example = "Water quality sampling for river monitoring")
    private String samplingNotes;

    public String getPlanCode() {
        return planCode;
    }

    public void setPlanCode(String planCode) {
        this.planCode = planCode;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public String getSamplingWindowStartDate() {
        return samplingWindowStartDate;
    }

    public void setSamplingWindowStartDate(String samplingWindowStartDate) {
        this.samplingWindowStartDate = samplingWindowStartDate;
    }

    public String getSamplingWindowEndDate() {
        return samplingWindowEndDate;
    }

    public void setSamplingWindowEndDate(String samplingWindowEndDate) {
        this.samplingWindowEndDate = samplingWindowEndDate;
    }

    public Integer getSampleVolumeQuantity() {
        return sampleVolumeQuantity;
    }

    public void setSampleVolumeQuantity(Integer sampleVolumeQuantity) {
        this.sampleVolumeQuantity = sampleVolumeQuantity;
    }

    public String getSampleVolumeUnit() {
        return sampleVolumeUnit;
    }

    public void setSampleVolumeUnit(String sampleVolumeUnit) {
        this.sampleVolumeUnit = sampleVolumeUnit;
    }

    public Integer getPriorityLevel() {
        return priorityLevel;
    }

    public void setPriorityLevel(Integer priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
        this.statusProvided = true;
    }

    @JsonIgnore
    public boolean isStatusProvided() {
        return statusProvided;
    }

    public String getSamplingNotes() {
        return samplingNotes;
    }

    public void setSamplingNotes(String samplingNotes) {
        this.samplingNotes = samplingNotes;
    }
}
