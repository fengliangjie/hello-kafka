package com.siemens.multitenancy.configuration.exception;

import com.siemens.pcf.common.entity.ResponseResult;
import com.siemens.pcf.common.exception.BusinessException;
import com.siemens.pcf.common.exception.ExceptionData;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

/**
 * Global exception handler.
 *
 * @author pdai
 */
@Slf4j
@Component
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * exception handler for bad request.
     *
     * @param e
     *            exception
     * @return ResponseResult
     */
    @ResponseBody
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = { BindException.class, ValidationException.class, MethodArgumentNotValidException.class })
    public ResponseResult<ExceptionData> handleParameterVerificationException(@NonNull Exception e) {
        ExceptionData.ExceptionDataBuilder exceptionDataBuilder = ExceptionData.builder();
        log.warn("Exception: {}", e.getMessage());
        if (e instanceof BindException) {
            BindingResult bindingResult = ((BindException) e).getBindingResult();
            bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .forEach(exceptionDataBuilder::error);
        } else if (e instanceof ConstraintViolationException) {
            if (e.getMessage() != null) {
                exceptionDataBuilder.error(e.getMessage());
            }
        } else {
            exceptionDataBuilder.error("invalid parameter");
        }
        return ResponseResult.fail(exceptionDataBuilder.build(), "invalid parameter");
    }

    /**
     * handle business exception.
     *
     * @param businessException
     *            business exception
     * @return ResponseResult
     */
    @ResponseBody
    @ExceptionHandler(BusinessException.class)
    public ResponseResult<BusinessException> processBusinessException(BusinessException businessException) {
        log.error(businessException.getLocalizedMessage(), businessException);
        return ResponseResult.fail(null,
                businessException.getLocalizedMessage() == null
                        ? com.siemens.pcf.common.entity.ResponseStatus.HTTP_STATUS_500.getDescription()
                        : businessException.getLocalizedMessage());
    }

    /**
     * handle other exception.
     *
     * @param exception
     *            exception
     * @return ResponseResult
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseResult<Exception> processException(Exception exception) {
        log.error(exception.getLocalizedMessage(), exception);
        return ResponseResult.fail(null, com.siemens.pcf.common.entity.ResponseStatus.HTTP_STATUS_500.getDescription());
    }

}
