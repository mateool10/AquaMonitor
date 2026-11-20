package com.aquamonitor.platform.pc216712u202422549.shared.interfaces.rest.transform;

import com.aquamonitor.platform.pc216712u202422549.shared.application.result.ApplicationError;
import com.aquamonitor.platform.pc216712u202422549.shared.application.result.Result;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.function.Function;

/**
 * Assembler that translates application Result values into HTTP responses.
 *
 * @author Jorge Mateo León Naupari
 */
public final class ResponseEntityAssembler {

    private ResponseEntityAssembler() {
    }

    public static <T, R> ResponseEntity<?> toResponseEntityFromResult(
            Result<T, ApplicationError> result,
            Function<T, R> successResourceAssembler,
            HttpStatusCode successStatus
    ) {
        return switch (result) {
            case Result.Success<T, ApplicationError> success ->
                    new ResponseEntity<>(successResourceAssembler.apply(success.value()), successStatus);
            case Result.Failure<T, ApplicationError> failure ->
                    MessageResourceFromApplicationErrorAssembler.toResponseFromApplicationError(failure.error());
        };
    }
}
