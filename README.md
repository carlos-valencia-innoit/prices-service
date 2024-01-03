# Technical challenge for price-services

## Description

En la base de datos de comercio electrónico de la compañía disponemos
de la tabla PRICES que refleja el precio final (pvp) y la tarifa que
aplica a un producto de una cadena entre unas fechas determinadas. A
continuación se muestra un ejemplo de la tabla con los campos
relevantes:
PRICES
-------

### Campos:
* BRAND_ID: foreign key de la cadena del grupo (1 = ZARA).
* START_DATE , END_DATE: rango de fechas en el que aplica el precio tarifa indicado.
* PRICE_LIST: Identificador de la tarifa de precios aplicable.
* PRODUCT_ID: Identificador código de producto.
* PRIORITY: Desambiguador de aplicación de precios. Si dos tarifas coinciden en un rago de fechas se aplica la de mayor prioridad (mayor valor numérico).
* PRICE: precio final de venta.
* CURR: iso de la moneda.

## Se pide:
* Construir una aplicación/servicio en SpringBoot que provea una end
point rest de consulta tal que:
* Acepte como parámetros de entrada: fecha de aplicación, identificador
de producto, identificador de cadena.
* Devuelva como datos de salida: identificador de producto,
identificador de cadena, tarifa a aplicar, fechas de aplicación y
precio final a aplicar.
* Se debe utilizar una base de datos en memoria (tipo h2) e inicializar
con los datos del ejemplo, (se pueden cambiar el nombre de los campos
y añadir otros nuevos si se quiere, elegir el tipo de dato que se
considere adecuado para los mismos).

### Desarrollar unos test al endpoint rest que validen las siguientes
peticiones al servicio con los datos del ejemplo:
- Test 1: petición a las 10:00 del día 14 del producto 35455
para la brand 1 (ZARA)
- Test 2: petición a las 16:00 del día 14 del producto 35455
para la brand 1 (ZARA)
- Test 3: petición a las 21:00 del día 14 del producto 35455
para la brand 1 (ZARA)
- Test 4: petición a las 10:00 del día 15 del producto 35455
para la brand 1 (ZARA)
- Test 5: petición a las 21:00 del día 16 del producto 35455
para la brand 1 (ZARA)
Se valorará:
Diseño y construcción del servicio.
Calidad de Código.
Resultados correctos en los test.


# Prices Service API Documentation

## Project Structure
The Prices Service API project is structured following the principles of hexagonal architecture, divided into distinct layers to separate concerns and ensure modularity. The project's main components are as follows:

- **Domain Layer**: Contains the business logic and domain models.
- **Application Layer**: Serves as the intermediate layer, handling the application logic.
- **Infrastructure Layer**: Manages data persistence, external interfaces, and web controllers.

### Directory Layout
![image](https://github.com/carlos-valencia-innoit/prices-service/assets/145386152/df48b79e-0bef-4ab7-a6a9-6d095813b90d)

## Architecture and Design
- **Hexagonal Architecture**: The project employs Hexagonal Architecture, also known as Ports and Adapters architecture, to isolate the core logic from external concerns. This approach enhances maintainability and facilitates testing.
- **Spring Boot**: The project is built using Spring Boot, leveraging its powerful features such as dependency injection, auto-configuration, and embedded servers.

## Testing Strategy
- **Unit Testing**: Tests individual components in isolation, ensuring that each part performs as expected.
- **Integration Testing**: Validates the interaction between different layers and components, such as the interaction between the controller, service, and repository layers.
- **ArchUnit Testing**: Ensures adherence to architectural rules, like package dependencies and layer interactions.

## GitHub Actions
- **Continuous Integration**: The project uses GitHub Actions for continuous integration. On each commit or pull request, it runs a series of automated tasks including:
  - Building the project.
  - Running tests and generating reports.
  - Ensuring code coverage thresholds are met.

## Key Technical Specifications
- **Java Version**: 17
- **Database**: H2 (In-memory database with PostgreSQL compatibility mode).
- **Build Tool**: Maven
- **API Documentation**: OpenAPI 3.0
- **Testing Frameworks**: JUnit, Mockito, ArchUnit
- **Additional Libraries**: Lombok, MapStruct

## API Endpoints
- **Retrieve Price Information (`GET /prices`)**:
  - **Parameters**: `applicationDate`, `productId`, `chainId`
  - **Responses**:
    - `200`: Success (Returns `PriceResponse`)
    - `400`: Bad Request (Returns `ErrorResponse`)
    - `500`: Internal Server Error (Returns `ErrorResponse`)
  - **Example API call**: http://localhost:8080/prices?applicationDate=2020-06-14T16:00Z&productId=35455&chainId=1
 
  ![image](https://github.com/carlos-valencia-innoit/prices-service/assets/145386152/b4eecd79-32c1-4ece-8ae2-f3977a47da24)
  ![image](https://github.com/carlos-valencia-innoit/prices-service/assets/145386152/8b3540c9-d27c-4e2f-887a-7088c81e6459)
  ![image](https://github.com/carlos-valencia-innoit/prices-service/assets/145386152/7087b8b8-056e-4829-994e-2490e76c11e6)






