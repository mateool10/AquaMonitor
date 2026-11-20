package com.aquamonitor.platform.pc216712u202422549.monitoring.application.internal.commandservices;

import com.aquamonitor.platform.pc216712u202422549.monitoring.application.commands.CreateSamplingPlanCommand;
import com.aquamonitor.platform.pc216712u202422549.monitoring.application.commandservices.SamplingPlanCommandService;
import com.aquamonitor.platform.pc216712u202422549.monitoring.domain.model.aggregates.SamplingPlan;
import com.aquamonitor.platform.pc216712u202422549.monitoring.domain.model.valueobjects.*;
import com.aquamonitor.platform.pc216712u202422549.monitoring.domain.repositories.SamplingPlanRepository;
import com.aquamonitor.platform.pc216712u202422549.shared.application.result.ApplicationError;
import com.aquamonitor.platform.pc216712u202422549.shared.application.result.Result;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.UUID;

/**
 * Implementation of the sampling plan command service.
 *
 * @author Jorge Mateo León Naupari
 */
@Service
public class SamplingPlanCommandServiceImpl implements SamplingPlanCommandService {
    private final SamplingPlanRepository samplingPlanRepository;

    public SamplingPlanCommandServiceImpl(SamplingPlanRepository samplingPlanRepository) {
        this.samplingPlanRepository = samplingPlanRepository;
    }

    @Override
    public Result<SamplingPlan, ApplicationError> handle(CreateSamplingPlanCommand command) {
        try {
            var planCode = new PlanCode(command.planCode());
            if (samplingPlanRepository.existsByPlanCode(planCode)) {
                return Result.failure(toPlanCodeConflict(command.planCode()));
            }

            var samplingPlan = new SamplingPlan(
                    planCode,
                    new StationId(UUID.fromString(command.stationId())),
                    new SamplingWindow(LocalDate.parse(command.samplingWindowStartDate()),
                            LocalDate.parse(command.samplingWindowEndDate())),
                    new SampleVolume(command.sampleVolumeQuantity(), command.sampleVolumeUnit()),
                    command.priorityLevel(),
                    toSamplingPlanStatus(command.status()),
                    command.samplingNotes());
            return Result.success(samplingPlanRepository.save(samplingPlan));
        } catch (DateTimeParseException e) {
            return Result.failure(ApplicationError.validationError("SamplingPlan",
                    "Sampling window dates must use the format yyyy-MM-dd"));
        } catch (IllegalArgumentException e) {
            return Result.failure(ApplicationError.validationError("SamplingPlan", e.getMessage()));
        } catch (DataIntegrityViolationException e) {
            return Result.failure(toPlanCodeConflict(command.planCode()));
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected("Sampling plan creation", e.getMessage()));
        }
    }

    private ApplicationError toPlanCodeConflict(String planCode) {
        return ApplicationError.conflict("Sampling_Plan",
                "A sampling plan with plan code '%s' already exists".formatted(planCode));
    }

    private SamplingPlanStatus toSamplingPlanStatus(String status) {
        if (status == null) {
            return SamplingPlanStatus.PENDING;
        }
        return SamplingPlanStatus.valueOf(status.trim().toUpperCase());
    }
}
