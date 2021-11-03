package pl.rekeep.core.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import javax.validation.ConstraintViolation;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class ApiException {

    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String message;
    private String debugMessage;
    private List<ApiSubException> subExceptions;

    private ApiException() {
        timestamp = LocalDateTime.now();
    }

    public ApiException(HttpStatus status) {
        this();
        this.status = status;
    }

    public ApiException(HttpStatus status, String message, Throwable ex) {
        this();
        this.status = status;
        this.message = message;
        this.debugMessage = ex.getLocalizedMessage();
    }

    private void addSubException(ApiSubException subException) {
        if (subExceptions == null) {
            subExceptions = new ArrayList<>();
        }
        subExceptions.add(subException);
    }

    private void addValidationException(String object, String field, Object rejectedValue, String message) {
        addSubException(new ApiValidationException(object, field, rejectedValue, message));
    }

    private void addValidationException(String object, String message) {
        addSubException(new ApiValidationException(object, message));
    }

    private void addValidationException(FieldError fieldException) {
        this.addValidationException(
                fieldException.getObjectName(),
                fieldException.getField(),
                fieldException.getRejectedValue(),
                fieldException.getDefaultMessage());
    }

    public void addValidationExceptions(List<FieldError> fieldExceptions) {
        fieldExceptions.forEach(this::addValidationException);
    }

    private void addValidationException(ObjectError objectError) {
        this.addValidationException(objectError.getObjectName(), objectError.getDefaultMessage());
    }

    public void addValidationException(List<ObjectError> globalExceptions) {
        globalExceptions.forEach(this::addValidationException);
    }

    private void addValidationException(ConstraintViolation<?> cv) {
        this.addValidationException(
                cv.getRootBeanClass().getSimpleName(),
                ((PathImpl) cv.getPropertyPath()).getLeafNode().asString(),
                cv.getInvalidValue(),
                cv.getMessage());
    }

    public void addValidationExceptions(Set<ConstraintViolation<?>> constraintViolations) {
        constraintViolations.forEach(this::addValidationException);
    }
}
