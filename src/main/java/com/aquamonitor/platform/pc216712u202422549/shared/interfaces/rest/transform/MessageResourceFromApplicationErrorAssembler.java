package com.aquamonitor.platform.pc216712u202422549.shared.interfaces.rest.transform;

import com.aquamonitor.platform.pc216712u202422549.shared.application.result.ApplicationError;
import com.aquamonitor.platform.pc216712u202422549.shared.interfaces.rest.resources.MessageResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.Locale;

import static com.aquamonitor.platform.pc216712u202422549.shared.infrastructure.i18n.LocalizedMessageResolver.resolveDetail;
import static com.aquamonitor.platform.pc216712u202422549.shared.infrastructure.i18n.LocalizedMessageResolver.resolveMessageOrDefault;

/**
 * Assembler that transforms an {@link ApplicationError} into an HTTP response with a localized {@link MessageResource}.
 *
 * @author Jorge Mateo León Naupari
 */
public final class MessageResourceFromApplicationErrorAssembler {

    private MessageResourceFromApplicationErrorAssembler() {
    }

    public static ResponseEntity<MessageResource> toResponseFromApplicationError(ApplicationError error) {
        return ResponseEntity.status(toStatusFromErrorCode(error.code()))
                .body(new MessageResource(toLocalizedMessage(error)));
    }

    public static HttpStatusCode toStatusFromErrorCode(String errorCode) {
        return switch (errorCode) {
            case "VALIDATION_ERROR" -> HttpStatus.BAD_REQUEST;
            case String s when s.endsWith("_NOT_FOUND") -> HttpStatus.NOT_FOUND;
            case "BUSINESS_RULE_VIOLATION" -> HttpStatusCode.valueOf(422);
            case String s when s.endsWith("_CONFLICT") -> HttpStatus.CONFLICT;
            default -> HttpStatus.INTERNAL_SERVER_ERROR;
        };
    }

    private static String toLocalizedMessage(ApplicationError error) {
        var specificKey = "error.%s.message".formatted(error.code().toLowerCase(Locale.ROOT).replace('_', '-'));
        var specificMessage = resolveMessageOrDefault(specificKey, null);
        if (specificMessage != null) {
            return specificMessage;
        }
        return switch (error.code()) {
            case "VALIDATION_ERROR" -> "%s: %s".formatted(
                    resolveMessageOrDefault("error.validation.message", "Validation failed"),
                    resolveDetail(error.details()));
            case String s when s.endsWith("_CONFLICT") ->
                    resolveMessageOrDefault("error.conflict.message", "Resource conflict");
            case String s when s.endsWith("_NOT_FOUND") ->
                    resolveMessageOrDefault("error.not-found.message", "Resource not found");
            default -> resolveMessageOrDefault("error.unexpected.message", "Unexpected error");
        };
    }
}