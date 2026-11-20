package com.aquamonitor.platform.pc216712u202422549.monitoring.domain.repositories;

import com.aquamonitor.platform.pc216712u202422549.monitoring.domain.model.aggregates.SamplingPlan;
import com.aquamonitor.platform.pc216712u202422549.monitoring.domain.model.valueobjects.PlanCode;

/**
 * Sampling plan repository port of the domain layer.
 *
 * @author Jorge Mateo León Naupari
 */
public interface SamplingPlanRepository {

    SamplingPlan save(SamplingPlan samplingPlan);

    boolean existsByPlanCode(PlanCode planCode);
}
