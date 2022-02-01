package com.alkemy.DisneyAPIMickaelaTarazaga.controllers;
import com.alkemy.DisneyAPIMickaelaTarazaga.exceptions.*;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerHandler extends ResponseEntityExceptionHandler {

    // handleHttpMediaTypeNotSupported : triggers when the JSON is invalid
    @Override
    public ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> details = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        builder.append(ex.getContentType());
        builder.append(" media type is not supported. Supported media types are ");
        ex.getSupportedMediaTypes().forEach(t -> builder.append(t).append(", "));
        details.add(builder.toString());
        ErrorDetails error = new ErrorDetails(LocalDateTime.now(), "Invalid JSON", details);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // handleHttpMessageNotReadable : triggers when the JSON is malformed
    @Override
    public ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getMessage());
        ErrorDetails error = new ErrorDetails(LocalDateTime.now(), "Malformed JSON request", details);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // handleMethodArgumentNotValid : triggers when @Valid fails
    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> details = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        ErrorDetails error = new ErrorDetails(LocalDateTime.now(), "Validation Errors", details);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // handleMissingServletRequestParameter : triggers when there are missing parameters
    @Override
    public ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getParameterName() + " parameter is missing");
        ErrorDetails error = new ErrorDetails(LocalDateTime.now(), "Missing Parameters", details);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // handleMethodArgumentTypeMismatch : triggers when a parameter's type does not match
    @ExceptionHandler(value = {MethodArgumentTypeMismatchException.class})
    public ResponseEntity<ErrorDetails> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
        List<String> details = new ArrayList<>();
        details.add(ex.getMessage());
        ErrorDetails error = new ErrorDetails(LocalDateTime.now(), "Type Mismatch", details);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // handleConstraintViolationException : triggers when @Valid fails
    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ResponseEntity<ErrorDetails> handleConstraintViolationException(ConstraintViolationException ex) {
        List<String> details = new ArrayList<>();
        details.add(ex.getMessage());
        ErrorDetails error = new ErrorDetails(LocalDateTime.now(), "Constraint Violation", details);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // handleResourceNotFoundException : triggers when there is not resource with the specified ID in BDD
    @ExceptionHandler(value = {InvalidException.class})
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(InvalidException ex) {
        List<String> details = new ArrayList<>();
        details.add(ex.getMessage());
        ErrorDetails error = new ErrorDetails(LocalDateTime.now(), "Resource Not Found", details);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

      // handleNoHandlerFoundException : triggers when the handler method is invalid
    @Override
    public ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(String.format("Could not find the %s method for URL %s", ex.getHttpMethod(), ex.getRequestURL()));
        ErrorDetails error = new ErrorDetails(LocalDateTime.now(), "Method Not Found", details);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<ErrorDetails> IllegalArgumentException(IllegalArgumentException ex) {
        List<String> details = new ArrayList<>();
        details.add(ex.getMessage());
        ErrorDetails error = new ErrorDetails(LocalDateTime.now(), "Illegal Argument", details);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {BadCredentialsException.class})
    public ResponseEntity<ErrorDetails> handleBadCredentialsException(BadCredentialsException ex) {
        List<String> details = new ArrayList<>();
        details.add(ex.getMessage());
        ErrorDetails error = new ErrorDetails(LocalDateTime.now(), "Auth error", details);
        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }

}
