package com.aquamonitor.platform.pc216712u202422549.monitoring.infrastructure.persistence.jpa.adapters;

import com.aquamonitor.platform.pc216712u202422549.monitoring.domain.model.aggregates.SamplingPlan;
import com.aquamonitor.platform.pc216712u202422549.monitoring.domain.model.valueobjects.PlanCode;
import com.aquamonitor.platform.pc216712u202422549.monitoring.domain.repositories.SamplingPlanRepository;
import com.aquamonitor.platform.pc216712u202422549.monitoring.infrastructure.persistence.jpa.assemblers.SamplingPlanPersistenceAssembler;
import com.aquamonitor.platform.pc216712u202422549.monitoring.infrastructure.persistence.jpa.repositories.SamplingPlanPersistenceRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository adapter that connects the domain sampling plan repository with Spring Data JPA.
 *
 * @author Jorge Mateo León Naupari
 */
@Repository
public class SamplingPlanRepositoryImpl implements SamplingPlanRepository {

    private final SamplingPlanPersistenceRepository samplingPlanPersistenceRepository;

    public SamplingPlanRepositoryImpl(SamplingPlanPersistenceRepository samplingPlanPersistenceRepository) {
        this.samplingPlanPersistenceRepository = samplingPlanPersistenceRepository;
    }

    @Override
    public SamplingPlan save(SamplingPlan samplingPlan) {
        var savedEntity = samplingPlanPersistenceRepository.save(
                SamplingPlanPersistenceAssembler.toPersistenceFromDomain(samplingPlan));
        return SamplingPlanPersistenceAssembler.toDomainFromPersistence(savedEntity);
    }

    @Override
    public boolean existsByPlanCode(PlanCode planCode) {
        return samplingPlanPersistenceRepository.countByPlanCode(planCode) > 0;
    }
}
