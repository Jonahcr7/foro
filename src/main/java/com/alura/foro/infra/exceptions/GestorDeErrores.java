package com.alura.foro.infra.exceptions;

import com.alura.foro.domain.ValidacionException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GestorDeErrores {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity gestionarEror404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity gestionarEror400(MethodArgumentNotValidException ex) {
        var errores = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(errores.stream().map(DatosErrorDeValidacion::new).toList());
    }

    @ExceptionHandler(ValidacionException.class)
    public ResponseEntity gestionarErorDeValidacion(ValidacionException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity gestionarError500(DataIntegrityViolationException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    public record DatosErrorDeValidacion(String campo, String mensaje) {
        public DatosErrorDeValidacion(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
