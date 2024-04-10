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
3. Tener instalado MongoDB, como por ejemplo con DOCKER. Usar los siguientes comandos:
	- `docker pull mongo`
	- `docker run --name mongodb -p 27017:27017 -e MONGO_INITDB_ROOT_USERNAME=admin -e MONGO_INITDB_ROOT_PASSWORD=admin mongo`
4. Ejecuta el comando `gradle bootRun` en la terminal.
5. 
## Ejemplos de consumo de la API REST de ProductController

A continuación, se presentan algunos ejemplos de cómo puedes consumir la API REST del controlador `ProductController` utilizando `curl`:

- Obtener todos los productos:
  ```bash
  curl -X GET http://localhost:8130/api/products
- Obtener un producto por su ID:
  ```bash
  curl -X GET http://localhost:8130/api/products/{id}
- Crear un nuevo producto:  
  ```bash
  curl -X POST -H "Content-Type: application/json" -d '{"name":"TV 28 LG","price":500.0, "category":{"id":"1","name":"TV"}}' http://localhost:8130/api/products
- Actualizar un producto existente:
  ```bash  
  curl -X PUT -H "Content-Type: application/json" -d '{"name":"TV 28 LG","price":550.0, "category":{"id":"1","name":"TV"}}' http://localhost:8130/api/products/{id}
- Eliminar un producto:
  ```bash
  curl -X DELETE http://localhost:8130/api/products/{id}
- Subir una imagen para un producto:
  ```bash
  curl -X POST -H "Content-Type: multipart/form-data" -F "file=@ruta/a/la/imagen.jpg" http://localhost:8130/api/products/upload/{id}

Por favor, reemplaza {id} con el ID del producto que deseas obtener, actualizar, eliminar o subir una imagen. Para los comandos POST /api/products y PUT /api/products/{id}, asegúrate de reemplazar los valores de "nombre del producto", "descripción del producto" y "precio del producto" con los valores que deseas utilizar. Para el comando POST /api/products/upload/{id}, reemplaza "ruta/a/la/imagen.jpg" con la ruta al archivo de imagen que deseas subir.

## Autor

Este proyecto fue desarrollado por John Salazar.

## Licencia

Este proyecto es propiedad de John Salazar.
