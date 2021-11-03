package pl.rekeep.core.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
class ApiValidationException extends ApiSubException {

    private String object;
    private String field;
    private Object rejectedValue;
    private String message;

    ApiValidationException(String object, String message) {
        this.object = object;
        this.message = message;
    }
}
