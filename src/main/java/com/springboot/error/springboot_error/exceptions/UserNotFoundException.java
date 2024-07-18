package com.springboot.error.springboot_error.exceptions;

/**
 * La clase RuntimeException es una subclase de Exception en Java que representa
 * excepciones no comprobadas (unchecked exceptions). Estas excepciones no
 * necesitan ser declaradas en el método o en la firma del constructor usando
 * throws, y no necesitan ser capturadas o manejadas explícitamente.
 * 
 * Heredar de RuntimeException es una práctica común en Java cuando se quiere
 * crear excepciones personalizadas que no necesitan ser declaradas ni manejadas
 * explícitamente en el código. Esto es útil para representar errores de
 * programación que deben ser corregidos y para mantener el código más limpio y
 * manejable. Sin embargo, es importante usar esta característica con cuidado y
 * no abusar de las excepciones no comprobadas, ya que pueden dificultar la
 * identificación de errores en tiempo de compilación y reducir la robustez del
 * código.
 */

/**
 * Excepción lanzada cuando un usuario no es encontrado.
 */
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
