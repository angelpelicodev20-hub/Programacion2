# Laboratorio VII – APIs REST con Java y Spring Boot

Tres APIs REST independientes desarrolladas con **Java 21** y **Spring Boot 3.5**, organizadas por capas,
con almacenamiento en listas en memoria, validaciones, manejo de excepciones, pruebas unitarias
y protección mediante **API Key**.

| Ejercicio | Carpeta | Recurso | Filtro | Puerto | API Key (por defecto) |
|---|---|---|---|---|---|
| 1 | [Ejercicio1_API_Profesores](Ejercicio1_API_Profesores) | `/api/profesores` | `especialidad` | 8081 | `lab7-profesores-2026` |
| 2 | [Ejercicio2_API_Proveedores](Ejercicio2_API_Proveedores) | `/api/proveedores` | `categoria` | 8082 | `lab7-proveedores-2026` |
| 3 | [Ejercicio3_API_Empleados](Ejercicio3_API_Empleados) | `/api/empleados` | `especialidad` | 8083 | `lab7-empleados-2026` |

Cada ejercicio usa un puerto distinto, así que los tres pueden ejecutarse al mismo tiempo.

## Requisitos

- JDK 21 o superior
- Maven 3.9 o superior

## Ejecución

Desde la carpeta del ejercicio:

```bash
mvn spring-boot:run
```

Para ejecutar las pruebas unitarias:

```bash
mvn test
```

La API Key se puede cambiar con la variable de entorno `API_KEY` antes de iniciar la aplicación.

## Estructura por capas (igual en los tres ejercicios)

```
src/main/java/com/laboratorio7/<recurso>/
├── controller/   -> Endpoints REST (recibe la petición y devuelve el código HTTP)
├── service/      -> Interfaz con las reglas de negocio
│   └── impl/     -> Implementación del servicio
├── repository/   -> Almacenamiento en una lista en memoria
├── model/        -> Entidad de dominio y enum Estado (ACTIVO / INACTIVO)
├── dto/          -> Objetos de entrada (Request, con validaciones), salida (Response) y ApiError
├── mapper/       -> Conversión entre entidad y DTOs
├── exception/    -> Excepciones propias y GlobalExceptionHandler (@RestControllerAdvice)
└── security/     -> ApiKeyFilter: valida el encabezado X-API-KEY antes de llegar al controlador
```

## Endpoints

Todos los endpoints requieren el encabezado `X-API-KEY`. Ejemplo con el Ejercicio 1
(en los otros ejercicios cambia el recurso, el puerto y la clave):

| Método | Ruta | Descripción | Respuesta |
|---|---|---|---|
| POST | `/api/profesores` | Crear profesor | `201 Created` + encabezado `Location` |
| GET | `/api/profesores` | Listar todos | `200 OK` |
| GET | `/api/profesores?especialidad=Matemáticas` | Filtrar por especialidad (sin distinguir mayúsculas) | `200 OK` |
| GET | `/api/profesores/{id}` | Consultar por id | `200 OK` / `404 Not Found` |

### Códigos de error

| Código | Cuándo ocurre |
|---|---|
| `400 Bad Request` | Nombre o apellido vacío, correo inválido, campo obligatorio faltante, JSON mal formado, estado distinto de ACTIVO/INACTIVO o id no numérico |
| `401 Unauthorized` | Falta el encabezado `X-API-KEY` o la clave es incorrecta |
| `404 Not Found` | El registro consultado no existe |
| `409 Conflict` | Ya existe un registro con el mismo correo |

Formato de los errores:

```json
{
  "timestamp": "2026-11-06T22:50:00.123",
  "status": 400,
  "error": "Bad Request",
  "mensaje": "La solicitud contiene datos inválidos",
  "ruta": "/api/profesores",
  "errores": {
    "nombre": "El nombre no puede estar vacío",
    "correo": "El correo no tiene un formato válido"
  }
}
```

### Ejemplos con curl

```bash
curl -i -X POST http://localhost:8081/api/profesores -H "X-API-KEY: lab7-profesores-2026" -H "Content-Type: application/json" -d "{\"nombre\":\"Ana\",\"apellido\":\"Lopez\",\"correo\":\"ana.lopez@universidad.edu\",\"especialidad\":\"Matematicas\",\"estado\":\"ACTIVO\"}"
```

```bash
curl -i "http://localhost:8081/api/profesores?especialidad=Matematicas" -H "X-API-KEY: lab7-profesores-2026"
```

```bash
curl -i http://localhost:8081/api/profesores/1 -H "X-API-KEY: lab7-profesores-2026"
```

```bash
curl -i http://localhost:8081/api/profesores
```

Si `estado` no se envía al crear, el registro se crea como `ACTIVO`.

## Pruebas unitarias

Cada ejercicio incluye dos clases de prueba (12 pruebas en total por ejercicio):

- `*ServiceImplTest` – pruebas unitarias del servicio con el repositorio simulado (Mockito):
  crear registro, obtener registro existente, obtener registro inexistente (lanza la excepción),
  correo duplicado y filtro.
- `*ControllerTest` – pruebas de la capa web con `MockMvc`: `201` al crear, `200` al consultar
  un registro existente, `404` al consultar uno inexistente, `400` por validaciones y `401` sin
  API Key o con una API Key inválida.

## Colecciones de Postman

Cada ejercicio trae su colección en la carpeta `postman/`, lista para importar en Postman.
Ya incluye la URL base, la API Key y pruebas automáticas para cada petición. Ejecutar las peticiones en orden
con la aplicación recién iniciada (los datos viven en memoria y se borran al reiniciar).

También se pueden ejecutar desde la terminal con Postman CLI:

```bash
postman collection run postman/API_Profesores.postman_collection.json
```
