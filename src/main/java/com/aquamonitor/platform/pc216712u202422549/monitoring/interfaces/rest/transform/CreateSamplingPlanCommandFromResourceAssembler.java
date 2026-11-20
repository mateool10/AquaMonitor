package com.aquamonitor.platform.pc216712u202422549.monitoring.interfaces.rest.transform;

import com.aquamonitor.platform.pc216712u202422549.monitoring.application.commands.CreateSamplingPlanCommand;
import com.aquamonitor.platform.pc216712u202422549.monitoring.interfaces.rest.resources.CreateSamplingPlanResource;

/**
 * Assembler to convert a CreateSamplingPlanResource into a CreateSamplingPlanCommand.
 *
 * @author Jorge Mateo León Naupari
 */
public class CreateSamplingPlanCommandFromResourceAssembler {

    public static CreateSamplingPlanCommand toCommandFromResource(CreateSamplingPlanResource resource) {
        if (resource.isStatusProvided() && resource.getStatus() == null) {
            throw new IllegalArgumentException("Status must not be null when it is sent");
        }
        return new CreateSamplingPlanCommand(
                resource.getPlanCode(),
                resource.getStationId(),
                resource.getSamplingWindowStartDate(),
                resource.getSamplingWindowEndDate(),
                resource.getSampleVolumeQuantity(),
                resource.getSampleVolumeUnit(),
                resource.getPriorityLevel(),
                resource.getStatus(),
                resource.getSamplingNotes());
    }
}
