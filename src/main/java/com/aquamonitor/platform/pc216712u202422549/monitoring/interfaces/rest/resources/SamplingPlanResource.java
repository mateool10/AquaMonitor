package com.aquamonitor.platform.pc216712u202422549.monitoring.interfaces.rest.resources;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Resource returned by the API with the information of a sampling plan.
 *
 * @author Jorge Mateo León Naupari
 */
@Schema(name = "SamplingPlanResponse", description = "Sampling plan information response")
public record SamplingPlanResource(
        @Schema(description = "Sampling plan unique identifier", example = "1")
        Long id,

        @Schema(description = "Unique code of the sampling plan", example = "SAM-001")
        String planCode,

        @Schema(description = "Monitoring station identifier", example = "550e8400-e29b-41d4-a716-446655440000")
        String stationId,

        @Schema(description = "Sampling window start date", example = "2026-11-21")
        String samplingWindowStartDate,

        @Schema(description = "Sampling window end date", example = "2026-11-25")
        String samplingWindowEndDate,

        @Schema(description = "Volume of water to collect", example = "10")
        Integer sampleVolumeQuantity,

        @Schema(description = "Unit of the sample volume", example = "LITER")
        String sampleVolumeUnit,

        @Schema(description = "Priority level from 1 to 5", example = "3")
        Integer priorityLevel,

        @Schema(description = "Status of the sampling plan", example = "PENDING")
        String status,

        @Schema(description = "Notes of the sampling plan", example = "Water quality sampling for river monitoring")
        String samplingNotes
) {
}
