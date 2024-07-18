package com.springboot.error.springboot_error.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.springboot.error.springboot_error.exceptions.UserNotFoundException;
import com.springboot.error.springboot_error.models.Error;

/**
 * La anotación @RestControllerAdvice en Spring se utiliza para manejar
 * excepciones de manera centralizada en aplicaciones web que usan controladores
 * RESTful. Es una especialización de @ControllerAdvice que también agrega la
 * funcionalidad de @ResponseBody, lo que significa que los métodos manejadores
 * de excepciones anotados con @ExceptionHandler dentro de una
 * clase @RestControllerAdvice devolverán directamente respuestas JSON.
 * 
 * Propósito y Uso de @RestControllerAdvice
 * Manejo Centralizado de Excepciones: Permite capturar y manejar excepciones en
 * un solo lugar en lugar de hacerlo en cada controlador individualmente.
 * Consistencia en Respuestas de Error: Asegura que todas las respuestas de
 * error sigan un formato uniforme en toda la aplicación.
 * Separación de Concerns: Mantiene el código de manejo de excepciones separado
 * del código de negocio en los controladores.
 * 
 * Ventajas de Usar @RestControllerAdvice
 * Centralización: Facilita el manejo centralizado de errores, lo que simplifica
 * el mantenimiento y la actualización del manejo de excepciones en la
 * aplicación.
 * Consistencia: Garantiza respuestas de error consistentes en todo el servicio
 * REST.
 * Simplificación del Código de Controlador: Libera a los controladores de
 * manejar las excepciones directamente, permitiéndoles centrarse en la lógica
 * del negocio.
 * Integración en una Aplicación Real
 * En una aplicación real, podrías tener diferentes excepciones específicas de
 * la aplicación que necesiten diferentes manejadores:
 * 
 * Excepciones de Validación: Manejadas con detalles sobre qué campos fallaron.
 * Excepciones de Seguridad: Manejadas con respuestas que indican problemas de
 * autorización o autenticación.
 * Excepciones Genéricas: Para capturar cualquier otra excepción inesperada y
 * responder con un mensaje de error genérico.
 * 
 */

/**
 * Controlador de manejo de excepciones a nivel de aplicación para controladores
 * REST.
 */
@RestControllerAdvice
public class HandlerExceptionController {

    /**
     * ResponseEntity es una clase fundamental en Spring Framework que se utiliza
     * para representar toda la respuesta HTTP, incluida la estructura de
     * encabezados, el cuerpo y el código de estado. Es especialmente útil cuando se
     * necesita un control total sobre la respuesta HTTP.
     * 
     * ¿Qué es ResponseEntity?
     * ResponseEntity<T> es un contenedor genérico para cualquier tipo de respuesta
     * HTTP. El tipo genérico T representa el tipo del cuerpo de la respuesta. Con
     * ResponseEntity, puedes:
     * 
     * Especificar el cuerpo de la respuesta.
     * Especificar los encabezados HTTP.
     * Especificar el código de estado HTTP.
     * Ventajas de Usar ResponseEntity
     * Flexibilidad: Permite un control total sobre la respuesta HTTP.
     * Claridad: Hace explícito el tipo de datos devueltos y los metadatos de la
     * respuesta.
     * Completitud: Puede incluir el cuerpo, encabezados y código de estado HTTP en
     * una sola estructura.
     * 
     * Métodos Comunes de ResponseEntity
     * ok(T body): Devuelve un ResponseEntity con el código de estado HTTP 200 (OK)
     * y el cuerpo proporcionado.
     * status(HttpStatus status): Crea un ResponseEntity con el código de estado
     * HTTP especificado.
     * badRequest(): Devuelve un ResponseEntity con el código de estado HTTP 400
     * (Bad Request).
     * notFound(): Devuelve un ResponseEntity con el código de estado HTTP 404 (Not
     * Found).
     * noContent(): Devuelve un ResponseEntity con el código de estado HTTP 204 (No
     * Content).
     * headers(HttpHeaders headers): Añade encabezados HTTP a la respuesta.
     */

    /**
     * Maneja excepciones de división por cero.
     * 
     * @param e La excepción lanzada.
     * @return Un ResponseEntity con detalles del error.
     */
    @ExceptionHandler({ ArithmeticException.class })
    public ResponseEntity<Error> divisionByZero(Exception e) {
        Error error = new Error();
        error.setDate(new Date());
        error.setError("Error de división por cero");
        error.setMessage(e.getMessage());
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        // return ResponseEntity.internalServerError().body(error);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(error);
    }

    /**
     * NoHandlerFoundException
     * Esta es una excepción específica en Spring que se lanza cuando no se
     * encuentra un manejador para la solicitud actual. Esto ocurre típicamente
     * cuando se realiza una solicitud a una URL que no tiene ningún mapeo definido
     * en la aplicación.
     *
     */
    /**
     * Maneja excepciones de recurso no encontrado.
     * 
     * @param e La excepción lanzada.
     * @return Un ResponseEntity con detalles del error.
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Error> notFoundEx(NoHandlerFoundException e) {
        Error error = new Error();
        error.setDate(new Date());
        error.setError("Api Rest No Encontrada");
        error.setMessage(e.getMessage());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(error);
    }

    /**
     * Maneja excepciones de formato de número.
     * 
     * @param e La excepción lanzada.
     * @return Un mapa con detalles del error.
     */
    @ExceptionHandler(NumberFormatException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, String> numberFormatException(Exception e) {
        Map<String, String> error = new HashMap<>();
        error.put("Date", new Date().toString());
        error.put("Error", "Numero invalido o incorrecto, no tiene formato de dígito");
        error.put("Message", e.getMessage());
        error.put("Status", HttpStatus.INTERNAL_SERVER_ERROR.toString());

        return error;
    }

    /**
     * La excepción HttpMessageNotWritableException en Spring Framework es lanzada
     * cuando el servidor no puede escribir la respuesta HTTP, generalmente debido a
     * un problema con la conversión del objeto de respuesta a una representación
     * compatible (como JSON o XML). Esta excepción puede ocurrir, por ejemplo,
     * cuando hay un error en la serialización del objeto de respuesta.
     */
    /**
     * Maneja excepciones de puntero nulo, mensajes HTTP no escribibles y usuario no
     * encontrado.
     * 
     * @param e La excepción lanzada.
     * @return Un mapa con detalles del error.
     */
    @ExceptionHandler({ NullPointerException.class,
            HttpMessageNotWritableException.class,
            UserNotFoundException.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, String> userNotFoundException(Exception e) {
        Map<String, String> error = new HashMap<>();
        error.put("Date", new Date().toString());
        error.put("Error", "El usuario o role no existe");
        error.put("Message", e.getMessage());
        error.put("Status", HttpStatus.INTERNAL_SERVER_ERROR.toString());

        return error;
    }

}
