package com.aquamonitor.platform.pc216712u202422549.monitoring.infrastructure.persistence.jpa.converters;

import com.aquamonitor.platform.pc216712u202422549.monitoring.domain.model.valueobjects.PlanCode;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * Converts the plan code between the domain model and a text column.
 *
 * @author Jorge Mateo León Naupari
 */
@Converter(autoApply = false)
public class PlanCodePersistenceConverter implements AttributeConverter<PlanCode, String> {

    @Override
    public String convertToDatabaseColumn(PlanCode attribute) {
        return attribute == null ? null : attribute.code();
    }

    @Override
    public PlanCode convertToEntityAttribute(String dbData) {
        return dbData == null ? null : new PlanCode(dbData);
    }
}
