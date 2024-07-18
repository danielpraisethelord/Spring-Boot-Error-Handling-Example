# Spring Boot Error Handling Example

Este proyecto de Spring Boot demuestra cómo manejar diferentes tipos de errores utilizando un controlador centralizado de excepciones (`HandlerExceptionController`). La aplicación incluye funcionalidades para gestionar usuarios y roles, y define servicios para buscar y obtener usuarios.

## Estructura del Proyecto

- **controller**: Contiene los controladores que manejan las solicitudes HTTP.
- **exceptions**: Define las excepciones personalizadas utilizadas en la aplicación.
- **models**: Contiene las clases de dominio que representan los datos.
- **services**: Define los servicios que contienen la lógica de negocio.
- **AppConfig.java**: Configura los beans necesarios para la aplicación.
- **resources**: Contiene los archivos de configuración y recursos estáticos.

## Endpoints

### `/app`
Endpoint principal que intenta parsear un número desde una cadena incorrecta, lo que provoca una `NumberFormatException`.

### `/app/show/{id}`
Muestra la información de un usuario basado en su ID. Lanza una `UserNotFoundException` si el usuario no existe.

### `/app/show-v2/{id}`
Otra forma de mostrar la información de un usuario basado en su ID. Retorna un `404 Not Found` si el usuario no existe.

## Manejo de Excepciones

El controlador `HandlerExceptionController` maneja las siguientes excepciones:

- **ArithmeticException**: Maneja errores de división por cero.
- **NoHandlerFoundException**: Maneja errores cuando no se encuentra un manejador para la solicitud actual.
- **NumberFormatException**: Maneja errores de formato de número inválido.
- **NullPointerException, HttpMessageNotWritableException, UserNotFoundException**: Maneja errores cuando el usuario o el rol no existen o hay problemas al escribir la respuesta HTTP.

## Descripción de Clases

### AppController

Este controlador maneja las solicitudes relacionadas con la aplicación. 

- **Métodos**:
  - `index()`: Intenta parsear un número desde una cadena incorrecta.
  - `show(Long id)`: Muestra la información de un usuario basado en su ID. Lanza una `UserNotFoundException` si el usuario no existe.
  - `showV2(Long id)`: Otra forma de mostrar la información de un usuario basado en su ID. Retorna un `404 Not Found` si el usuario no existe.

### HandlerExceptionController

Este controlador maneja las excepciones de manera centralizada.

- **Métodos**:
  - `divisionByZero(Exception e)`: Maneja errores de división por cero.
  - `notFoundEx(NoHandlerFoundException e)`: Maneja errores cuando no se encuentra un manejador para la solicitud actual.
  - `numberFormatException(Exception e)`: Maneja errores de formato de número inválido.
  - `userNotFoundException(Exception e)`: Maneja errores cuando el usuario o el rol no existen o hay problemas al escribir la respuesta HTTP.

### UserNotFoundException

Excepción personalizada que se lanza cuando no se encuentra un usuario.

### Role

Clase de dominio que representa un rol.

- **Atributos**:
  - `name`: Nombre del rol.

### User

Clase de dominio que representa un usuario.

- **Atributos**:
  - `id`: Identificador del usuario.
  - `name`: Nombre del usuario.
  - `lastname`: Apellido del usuario.
  - `role`: Rol del usuario.

### Error

Clase que representa un error.

- **Atributos**:
  - `message`: Mensaje del error.
  - `error`: Descripción del error.
  - `status`: Código de estado HTTP.
  - `date`: Fecha del error.

### UserService

Interfaz que define los métodos para manejar usuarios.

- **Métodos**:
  - `findAll()`: Devuelve una lista de todos los usuarios.
  - `findById(Long id)`: Devuelve un usuario basado en su ID.

### UserServiceImpl

Implementación de la interfaz `UserService`.

- **Métodos**:
  - `findAll()`: Devuelve una lista de todos los usuarios.
  - `findById(Long id)`: Devuelve un usuario basado en su ID.

### AppConfig

Clase de configuración que define los beans necesarios para la aplicación.

- **Métodos**:
  - `users()`: Devuelve una lista de usuarios predefinidos.

## Cómo Funciona la Aplicación

La aplicación se inicia y expone varios endpoints RESTful. Los controladores manejan las solicitudes HTTP y utilizan servicios para obtener los datos necesarios. Cuando ocurre una excepción, el `HandlerExceptionController` maneja el error y devuelve una respuesta apropiada.

El controlador `AppController` proporciona endpoints para mostrar información de usuarios y manejar excepciones comunes como `UserNotFoundException` y `NumberFormatException`.

El `HandlerExceptionController` centraliza el manejo de excepciones para proporcionar respuestas consistentes en caso de errores, mejorando la mantenibilidad y consistencia de la aplicación.
