package com.aquamonitor.platform.pc216712u202422549.monitoring.infrastructure.persistence.jpa.repositories;

import com.aquamonitor.platform.pc216712u202422549.monitoring.domain.model.valueobjects.PlanCode;
import com.aquamonitor.platform.pc216712u202422549.monitoring.infrastructure.persistence.jpa.entities.SamplingPlanPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data repository for sampling plan persistence entities.
 *
 * @author Jorge Mateo León Naupari
 */
@Repository
public interface SamplingPlanPersistenceRepository extends JpaRepository<SamplingPlanPersistenceEntity, Long> {

    @Query("select count(samplingPlan) from SamplingPlanPersistenceEntity samplingPlan where samplingPlan.planCode = :planCode")
    long countByPlanCode(@Param("planCode") PlanCode planCode);
}
