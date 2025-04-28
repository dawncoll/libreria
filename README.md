# Libreria

**Libreria** es un proyecto de ejemplo desarrollado en **Spring Boot**, **Thymeleaf** y usando **H2** como base de datos en memoria.  
El objetivo principal es proporcionar una base sobre la que el alumnado pueda comenzar a trabajar en el desarrollo de aplicaciones utilizando el modelo **Spring MVC**.

Este proyecto está pensado para fomentar buenas prácticas como:
- Separación de capas de **presentación**, **dominio** y **persistencia**.
- Uso de **repositorios**, **servicios** e **interfaces**.
- Implementación de **tests unitarios** con **JUnit 5**.

---

## Estructura del proyecto

- **`web`**: controladores y configuración de MVC.
- **`domain`**: entidades de dominio, mapeadores y servicios.
- **`persistence`**: entidades JPA, repositorios, paginación y utilidades.
- **`resources`**:
  - Plantillas **Thymeleaf** para la vista.
  - Archivos de configuración de **H2** y localización de mensajes.
- **`tests`**: pruebas unitarias de distintas capas usando **JUnit 5**.

---

## Características

- Proyecto **arrancable** en local.
- Base de datos en memoria **H2** con datos iniciales (`schema.sql`, `data.sql`).
- Controladores Thymeleaf con vistas básicas (`index`, `listaAutores`, `autorForm`, etc.).
- Ejemplos de **servicios** y **repositorios** configurados.
- Configuración de seguridad básica (`WebMVCSecurity`).
- Implementación de utilidades de **paginación**.
- Tests unitarios predefinidos para servicios, controladores y utilidades.

---

## Advertencias didácticas

Este proyecto contiene **errores intencionados** para ser trabajados en clase:

- **Duplicación de código** en los tests:
  - Creación de objetos `Autor` y `AutorEntity` manualmente en múltiples pruebas debido a no tener una clase responsable de su creación.
- **Responsabilidades cruzadas** en controladores:
  - Parte de la lógica de paginación y preparación de vistas está mezclada con la lógica de negocio.
- **Paginación no completamente desacoplada**.
- **Falta de tests** sobre el código inicial.

Estos errores permitirán al alumnado reflexionar, detectar problemas y aplicar refactorizaciones.

---

## Requisitos para ejecutar el proyecto

- **Java 17** o superior
- **Maven 3.8+**
- IDE recomendado: IntelliJ IDEA, Eclipse o VSCode

Para arrancarlo:

```bash
mvn spring-boot:run


---

La aplicación se inicia en:

[http://localhost:8080](http://localhost:8080)

Consola de H2 disponible en:

[http://localhost:8080/h2-console](http://localhost:8080/h2-console)

(JDBC URL: `jdbc:h2:mem:testdb`, usuario: `sa`, sin contraseña).

