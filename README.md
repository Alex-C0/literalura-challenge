# 📚 literalura-challenge

Challenge de **Spring Boot** del curso de **Alura**.  
Catálogo de libros desarrollado como **aplicación de consola**.

Proyecto desarrollado en **Java con Spring Boot**, que consume la API de **Gutendex** para consultar información de libros y autores, y persiste los datos en una base de datos **PostgreSQL** utilizando **Spring Data JPA**.

---

## 🎯 Objetivo del proyecto

El objetivo del proyecto es construir una aplicación que permita:

- Consumir datos externos desde una API REST
- Procesar y mapear la información recibida
- Persistir libros y autores en una base de datos relacional
- Consultar la información almacenada mediante un menú interactivo en consola

---

## 🛠️ Tecnologías utilizadas

- Java 17+
- Spring Boot
- Spring Data JPA
- Hibernate
- PostgreSQL
- Jackson (ObjectMapper)
- Maven

---

## 📦 Estructura del proyecto

El proyecto está organizado por capas:

- **model** → Entidades JPA (`Libro`, `Autor`)
- **dto** → DTOs para mapear la respuesta de la API
- **repository** → Repositorios JPA
- **service** → Lógica de negocio y consumo de la API
- **principal** → Menú de consola
- **application** → Clase principal con `CommandLineRunner`

---

## 📡 Consumo de la API

La aplicación consume la API pública de **Gutendex** para obtener información de libros y autores.

Para manejar la respuesta de la API se utiliza un DTO intermedio llamado  
**`RespuestaGutendex`**, que representa la estructura completa del JSON recibido.

---

## 🖥️ Funcionalidades disponibles

Al ejecutar la aplicación, el usuario puede interactuar con el siguiente menú:

Elija una opción:

1- Buscar libro por título
2- Listar libros registrados
3- Listar autores registrados
4- Listar autores vivos en un determinado año
5- Listar libros por idioma
0- Salir


- Los libros y autores se guardan en la base de datos
- Se evita la caída del programa ante entradas inválidas del usuario
- La salida por consola se muestra en formato legible (varias líneas)

---

## 🗄️ Base de datos

- Base de datos: **PostgreSQL**
- Las tablas se generan automáticamente usando JPA

### Relación entre entidades

- Un **Autor** puede tener muchos **Libros**
- Un **Libro** pertenece a un solo **Autor**

---

## ▶️ Cómo ejecutar el proyecto

1. Clonar el repositorio
2. Configurar la base de datos PostgreSQL
3. Ajustar el archivo `application.properties` con tus credenciales
4. Ejecutar la aplicación desde el IDE o desde la terminal con:

```bash
mvn spring-boot:run
```
Proyecto desarrollado por Alejandro Cortés Lara como parte del challenge de práctica con Spring Boot, 
JPA y consumo de APIs del curso de Alura.
