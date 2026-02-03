# 🚀 Foro Hub

![java](https://img.shields.io/badge/Java-17%2B-blue?logo=java) ![spring](https://img.shields.io/badge/Spring_Boot-3.3-brightgreen?logo=spring) ![maven](https://img.shields.io/badge/Maven-3.6%2B-red?logo=apache-maven)

Descripción breve  
Una aplicación backend en Java con Spring Boot que provee una API REST para un foro, permitiendo realizar todas las acciones necesarias para éste.

## Qué hace la aplicación ✨
• \- Expone endpoints REST para crear, leer, actualizar y eliminar tópicos (CRUD).  
• \- Valida entradas y maneja errores con respuestas JSON consistentes.  
• \- Integra persistencia SQL mediante JPA / Hibernate.  
• \- Soporta configuración por propiedades para entornos locales y producción.  
• \- Autenticación con token JWT y Bearer Key.
• \- Sistema de login seguro hasheando las contraseñas en la base de datos.

## Tecnologías principales 🛠️
• \- `Java` (JDK 17\+)  
• \- `Spring Boot` (Web, Data JPA, Security)  
• \- `Maven` (gestión de dependencias y build)  
• \- `SQL` (MySQL)  
• \- Desarrollo con `IntelliJ IDEA 2025.2.3`

## Características clave 💡
• \- Arquitectura basada en capas: controladores, servicios, repositorios.  
• \- DTOs y mapeo claro entre capas.  
• \- Manejo de errores centralizado y respuestas HTTP apropiadas.  
• \- Configuración externalizada y perfiles para desarrollo/producción.  
• \- Tests unitarios.

## Requisitos (Windows) ⚙️
• \- JDK 17 o superior instalado  
• \- Maven 3.6\+  
• \- Base de datos disponible (MySQL por defecto, compatible con PostgreSQL)  
• \- `IntelliJ IDEA` recomendado

## Cómo usar (Windows, pasos simples) 📦
1. Clonar el repositorio:  
   `git clone <url-del-repo>`  
   `cd <nombre-del-repo>`
2. Configurar credenciales y URL de la BD en `src/main/resources/application.properties` (o `application.yml`), por ejemplo:  
   `spring.datasource.url=jdbc:postgresql://localhost:5432/mi_db`  
   `spring.datasource.username=tu_usuario`  
   `spring.datasource.password=tu_contraseña`
3. Construir el proyecto:  
   `mvn clean package`
4. Ejecutar en modo dev:  
   `mvn spring-boot:run`  
   o ejecutar el JAR generado:  
   `java -jar target/<nombre-del-artifact>.jar`
5. Acceder a la API en: `http://localhost:8080` (o el puerto configurado)

 ## Ejemplos de uso 📡
Recomiendo utilizar el software "Insomnia" para simular la comunicación entre el backend y el frontend o utilizar la documentación como prueba.

## Documentación 📖

La API cuenta con documentación interactiva generada con **Swagger UI**, disponible al ejecutar la aplicación en entorno local.

### ▶️ Acceder a Swagger
1. Asegurate de que la aplicación esté en ejecución.
2. Abrí tu navegador y accedé a una de las siguientes URLs:

http://localhost:8080/swagger-ui.html  
o  
http://localhost:8080/swagger-ui/index.html

> El puerto puede variar si se modifica la configuración (`server.port`).

### 🔐 Autenticación en Swagger
Algunos endpoints están protegidos y requieren autenticación mediante **JWT Bearer Token**.

Pasos:
1. Utilizar el endpoint de **login** para obtener el token.
2. En Swagger, hacer clic en el botón **Authorize**.
3. Ingresar el token obtenido desde el login

## Estructura general del proyecto 🧭
• \- `src/main/java` \- código fuente (controladores, servicios, repositorios)  
• \- `src/main/resources` \- configuración y assets (p. ej. `application.properties`)  
• \- `pom.xml` \- definición de dependencias y plugins Maven

## Configuración importante (variables comunes) 🔧
• \- `spring.datasource.url`  
• \- `spring.datasource.username`  
• \- `spring.datasource.password`  

## Cómo contribuir 🤝
• \- Crear una rama por feature: `feature/<descripcion>`  
• \- Abrir Pull Request con descripción clara del cambio y ejemplos de uso.  
• \- Mantener estilo de código y buenas prácticas (documentación en métodos clave).

## Estado del proyecto 📌
Finalizado como parte de un desafío técnico del programa ONE de Oracle y Alura Latam.


## Contacto y más info 📞
• \- Autor: Tomás Moya  
• \- Email: [tomasmoya5906@gmail.com](mailto:tomasmoya5906@email.com)  
• \- LinkedIn: [linkedin.com/in/tomas-moya](https://www.linkedin.com/in/tomas-moya)
