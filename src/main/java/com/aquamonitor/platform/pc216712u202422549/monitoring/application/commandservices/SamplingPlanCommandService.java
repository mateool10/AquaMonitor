package com.aquamonitor.platform.pc216712u202422549.monitoring.application.commandservices;

import com.aquamonitor.platform.pc216712u202422549.monitoring.application.commands.CreateSamplingPlanCommand;
import com.aquamonitor.platform.pc216712u202422549.monitoring.domain.model.aggregates.SamplingPlan;
import com.aquamonitor.platform.pc216712u202422549.shared.application.result.ApplicationError;
import com.aquamonitor.platform.pc216712u202422549.shared.application.result.Result;

/**
 * Command service of the monitoring bounded context.
 *
 * @author Jorge Mateo León Naupari
 */
public interface SamplingPlanCommandService {

    Result<SamplingPlan, ApplicationError> handle(CreateSamplingPlanCommand command);
}
