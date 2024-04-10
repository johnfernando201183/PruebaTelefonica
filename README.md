# PruebaTelefonica

Este es un proyecto de Spring Boot que utiliza Gradle como sistema de construcción. El proyecto implementa una API REST para la gestión de productos.

## Tecnologías y herramientas utilizadas

- Java 17
- Spring Boot
- Gradle
- IntelliJ IDEA
- MongoDB

## Controladores

El proyecto contiene los siguientes controladores:

- `ProductController`: Este controlador gestiona todas las operaciones relacionadas con los productos. Proporciona endpoints para obtener todos los productos, obtener un producto por su ID, crear un nuevo producto, actualizar un producto existente, eliminar un producto y subir una imagen para un producto.

- `GET /api/products`: Obtiene todos los productos.
- `GET /api/products/{id}`: Obtiene un producto por su ID.
- `POST /api/products`: Crea un nuevo producto.
- `PUT /api/products/{id}`: Actualiza un producto existente.
- `DELETE /api/products/{id}`: Elimina un producto.
- `POST /api/products/upload/{id}`: Sube una imagen para un producto.

## Cómo ejecutar el proyecto

Para ejecutar este proyecto, sigue estos pasos:

1. Clona el repositorio en tu máquina local.
2. Abre el proyecto en IntelliJ IDEA.
3. Ejecuta el comando `gradle clean build` en la terminal.
4. Tener instalado MongoDB, como por ejemplo con DOCKER. Usar los siguientes comandos:
	- docker pull mongo
	- docker run --name mongodb -p 27017:27017 -e MONGO_INITDB_ROOT_USERNAME=admin -e MONGO_INITDB_ROOT_PASSWORD=admin mongo

## Autor

Este proyecto fue desarrollado por John Salazar.

## Licencia

Este proyecto es propiedad de John Salazar.
