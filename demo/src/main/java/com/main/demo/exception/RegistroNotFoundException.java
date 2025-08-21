package com.main.demo.exception;

public class RegistroNotFoundException extends RuntimeException {
    public RegistroNotFoundException() {
        super("Registro no encontrado");
    }
}
