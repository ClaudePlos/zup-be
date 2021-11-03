package pl.rekeep.core.exception.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.rekeep.core.exception.ApiException;
import pl.rekeep.core.i18n.Messages;

import javax.persistence.EntityNotFoundException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    private final Messages msg;

    @Autowired
    public AppExceptionHandler(Messages msg) {
        this.msg = msg;
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return buildResponseEntity(new ApiException(BAD_REQUEST, msg.get("apiex.malformed.request"), ex));
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiException apiException = new ApiException(BAD_REQUEST);
        apiException.setMessage(String.format(msg.get("apiex.not.url.method"), ex.getHttpMethod(), ex.getRequestURL()));
        apiException.setDebugMessage(ex.getMessage());
        return buildResponseEntity(apiException);
    }

    @ExceptionHandler(javax.validation.ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolation(javax.validation.ConstraintViolationException ex) {
        ApiException apiException = new ApiException(BAD_REQUEST);
        apiException.setMessage(msg.get("apiex.validation.error"));
        apiException.addValidationExceptions(ex.getConstraintViolations());
        return buildResponseEntity(apiException);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex) {
        ApiException apiException = new ApiException(NOT_FOUND);
        apiException.setMessage(ex.getMessage());
        return buildResponseEntity(apiException);
    }

    private ResponseEntity<Object> buildResponseEntity(ApiException apiException) {
        return new ResponseEntity<>(apiException, apiException.getStatus());
    }

}
