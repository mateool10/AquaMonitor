package com.aquamonitor.platform.pc216712u202422549.shared.interfaces.rest;

import com.aquamonitor.platform.pc216712u202422549.shared.application.result.ApplicationError;
import com.aquamonitor.platform.pc216712u202422549.shared.interfaces.rest.resources.MessageResource;
import com.aquamonitor.platform.pc216712u202422549.shared.interfaces.rest.transform.MessageResourceFromApplicationErrorAssembler;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.aquamonitor.platform.pc216712u202422549.shared.infrastructure.i18n.LocalizedMessageResolver.resolveMessageOrDefault;

/**
 * Global exception handler for the REST API.
 *
 * <p>Translates the exceptions that are not handled by the application layer
 * into responses with a localized {@link MessageResource}.</p>
 *
 * @author Jorge Mateo León Naupari
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles validation errors of the request body.
     *
     * @param ex the validation exception from @Valid binding
     * @return error response with BAD_REQUEST status and the invalid fields
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MessageResource> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        var fieldPrefix = resolveMessageOrDefault("validation.field.prefix", "Field");
        var details = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> "%s %s %s".formatted(fieldPrefix, error.getField(), error.getDefaultMessage()))
                .reduce((first, second) -> first + "; " + second)
                .orElse(resolveMessageOrDefault("validation.request.failed", "Request validation failed"));
        return MessageResourceFromApplicationErrorAssembler.toResponseFromApplicationError(
                ApplicationError.validationError("request-body", details));
    }

    /**
     * Handles request bodies that cannot be read, such as malformed JSON or values with a wrong type.
     *
     * @param ex the exception raised while reading the request body
     * @return error response with BAD_REQUEST status
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<MessageResource> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        return MessageResourceFromApplicationErrorAssembler.toResponseFromApplicationError(
                ApplicationError.validationError("request-body", "Request body is malformed or has invalid values"));
    }

    /**
     * Handles invalid arguments that were not handled by the application layer.
     *
     * @param ex the illegal argument exception
     * @return error response with BAD_REQUEST status
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<MessageResource> handleIllegalArgumentException(IllegalArgumentException ex) {
        return MessageResourceFromApplicationErrorAssembler.toResponseFromApplicationError(
                ApplicationError.validationError("request-argument", ex.getMessage()));
    }

    /**
     * Handles violations of database constraints, such as a duplicated unique value.
     *
     * @param ex the data integrity violation exception
     * @return error response with CONFLICT status
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<MessageResource> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        return MessageResourceFromApplicationErrorAssembler.toResponseFromApplicationError(
                ApplicationError.conflict("Resource", "A database constraint was violated"));
    }

    /**
     * Handles any other exception as an unexpected error.
     *
     * @param ex the exception
     * @return error response with INTERNAL_SERVER_ERROR status
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<MessageResource> handleException(Exception ex) {
        return MessageResourceFromApplicationErrorAssembler.toResponseFromApplicationError(
                ApplicationError.unexpected("global-exception-handler",
                        ex.getMessage() != null ? ex.getMessage() : "An unexpected error occurred"));
    }
}
