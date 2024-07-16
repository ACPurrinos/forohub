# forohub

Esta es mi resolución del último challenge del programa ONE.
El mismo consiste en crear una API Rest con temática de un foro, utilizando Spring.

En esta oportunidad, elegí realizar una versión con los requisitos mínimos, centrada en los tópicos, y que permite a los usuarios:
Crear un nuevo tópico;
Mostrar todos los tópicos creados;
Mostrar un tópico específico;
Actualizar un tópico;
Eliminar un tópico.

Además, la API REST incluye las siguientes funcionalidades:

Implementación de una base de datos para la persistencia de la información;
Servicio de autenticación/autorización para restringir el acceso a la información.

Tecnologías utilizadas:
Java JDK: versión 17 en adelante 
Maven: versión 4 en adelante
Spring Boot: versión 3 en adelante 
MySQL: versión 8 en adelante 
IDE (Entorno de desarrollo integrado) 

Dependencias para agregar al crear el proyecto con Spring Initializr:

Lombok
Spring Web
Spring Boot DevTools
Spring Data JPA
Flyway Migration
MySQL Driver
Validation
Spring Security

Observación: El challenge se centra en los tópicos, por lo que el diagrama 
representa una base de datos más completa pero no es obligatorio implementar todas 
las tablas presentes en él - es suficiente centrarse en la tabla de tópicos.

Para crear un tópico necesitan las siguientes informaciones:

id
título
mensaje
fecha de creación
status (estado del tópico)
autor
curso


## Endpoints utilizados

| Método | Ruta | Descripción |
||||
| GET | `/topicos` | Obtiene una lista de todos los tópicos. |
| GET | `/topicos/ultimos10` | Obtiene una lista de los últimos 10 tópicos creados. |
| GET | `/topicos/buscar?nombreCurso= `| Obtiene una lista de topicos que pertenecen a un curso. |
| POST | `/topicos` | Crea un nuevo tópico. |
| GET | `/topic0s/{id}` | Obtiene un tópico específico por su ID. |
| PUT | `/topics/{id}` | Actualiza un tópico existente. |
| DELETE | `/topics/{id}` | Elimina un tópico existente. |
| POST | `/login` | Autentica un usuario y genera un token JWT. |

El proceso de autenticación en la API se realiza con la implementación de un controller responsable de recibir las solicitudes de inicio de sesión.
Además, se utiliza una clase DTO para recibir los datos de inicio de sesión y contraseña, y luego autenticar al usuario en el método AuthenticationManager presente en la clase SecurityConfigurations.
se ha implementado el método "generarToken()", utilizando la biblioteca JWT para crear un token con el algoritmo HMAC256 y una contraseña. También se ha añadido la funcionalidad de configurar 
la fecha de expiración del token.
Después de la generación del token JWT, éste es utilizado para autenticar la gestión de registros de los tópicos, incluyendo acciones como creación, consulta, 
actualización o eliminación. 