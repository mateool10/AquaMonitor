package com.aquamonitor.platform.pc216712u202422549.monitoring.application.commands;

/**
 * Command to create a sampling plan.
 *
 * @param planCode                the plan code
 * @param stationId               the monitoring station id as UUID text
 * @param samplingWindowStartDate the start date in yyyy-MM-dd format
 * @param samplingWindowEndDate   the end date in yyyy-MM-dd format
 * @param sampleVolumeQuantity    the volume to collect
 * @param sampleVolumeUnit        the unit of the volume
 * @param priorityLevel           the priority level from 1 to 5
 * @param status                  the optional status
 * @param samplingNotes           the optional notes
 * @author Jorge Mateo León Naupari
 */
public record CreateSamplingPlanCommand(String planCode,
                                        String stationId,
                                        String samplingWindowStartDate,
                                        String samplingWindowEndDate,
                                        Integer sampleVolumeQuantity,
                                        String sampleVolumeUnit,
                                        Integer priorityLevel,
                                        String status,
                                        String samplingNotes) {
}
