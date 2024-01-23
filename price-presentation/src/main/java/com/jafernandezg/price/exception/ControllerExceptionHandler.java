package com.jafernandezg.price.exception;

import com.jafernandezg.price.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(value = {BadRequestException.class, MissingServletRequestParameterException.class})
    public ResponseEntity<ErrorResponseDTO> resourceBadRequestException(Exception exception, WebRequest request) {
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
        errorResponseDTO.setCode(HttpStatus.BAD_REQUEST.value());
        errorResponseDTO.setMessage(exception.getMessage());

        return new ResponseEntity<ErrorResponseDTO>(errorResponseDTO, HttpStatusCode.valueOf(errorResponseDTO.getCode()));
    }

    @ExceptionHandler(value = {NotFoundException.class, NoResourceFoundException.class})
    public ResponseEntity<ErrorResponseDTO> resourceNotFoundException(Exception notFoundException, WebRequest request) {
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
        errorResponseDTO.setCode(HttpStatus.NOT_FOUND.value());
        errorResponseDTO.setMessage(notFoundException.getMessage());

        return new ResponseEntity<ErrorResponseDTO>(errorResponseDTO, HttpStatusCode.valueOf(errorResponseDTO.getCode()));
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ErrorResponseDTO> resourceException(Exception exception, WebRequest request) {
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
        errorResponseDTO.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorResponseDTO.setMessage(exception.getMessage());

        return new ResponseEntity<ErrorResponseDTO>(errorResponseDTO, HttpStatusCode.valueOf(errorResponseDTO.getCode()));
    }

}
