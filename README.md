# PRICE MS

### Realización del ejercicio
Para la realización del ejercicio propuesto en el apartado [Enunciado](#item1), se ha optado por seguir un modelo basado en un arquitectura hexagonal para que sea más fácil su futura escalabilidad, entre otras opciones, y se ha separado el proyecto en diferentes módulos.
Cada módulo realiza una función diferente y aislada del resto de módulos, en este caso contamos con 5 módulos:
- Contract
- Application
- Presentation
- Service
- Persistence

En el módulo `Contract` se encuentra el contrato del proyecto, desarrollado con Swagger y siguiendo los principios de Api/Contract Firts para la realización de este ejercicio.  
En el módulo `Application` se encuentra la clase encargada de arrancar el proyecto.  
En el módulo `Presentation` se encuentran los "Controller" y los "Mappers" necesarios para recibir las llamadas exteriores y que sean correctamente utilizadas en el proyecto.  
En el módulo `Service` se encuentran los servicios encargados de la lógica de negocio y sus respectivos "Mappers".  
En el módulo `Persistence` se encuentran los "DAO" y las clases e interfaces necesarias para interactuar con la base de datos H2 en memoria. 

Para completar este ejercicio se ha hecho uso de los principios "SOLID" y "Clean Code" para facilitar un código robusto y limpio.  
A su vez, se ha hecho uso de patrones de diseño y buenas prácticas cómo el uso de TDD, "Controller Advice" para controlar las excepciones, uso de una base de datos por microservicio, uso de "DAOs" con el patrón Singleton, uso de Api/Contract First, librerías cómo Lombok o MapStruct para faciltiar la lectura de código y el uso de logs para debuggers y seguir trazas del código.

Para complementar al enunciado del ejercicio, se ha decidido crear 2 tablas más en base de datos, la tabla `products` y la tabla `brands`, para dar mayor robustez a la base de datos y perfeccionar las "Foreign Key" descritas en el enunciado.

Al ser un ejercicio aislado y no expuesto para front-end no es necesario, pero sería facil aplicar un patrón Backend For Frontend (BFF) y así aislar el artefacto `price-ms` del front-end mediante un artefacto intermedio llamado `price-bff`.

También podría "Dockerizarse" el proyecto y que fuese orquestado por Kubernetes o Docker-Compose a la hora de arrancar, crear réplicas, pero al ser un ejercicio pequeño no se ha creído necesario.

En el ejercicio, para la realización de tests se han utilizado tests unitarios y tests de integración con librería cómo JUnit o Mockito, además de herramientas externas cómo Postman.

<a name="item1"></a>
### Enunciado
En la base de datos de comercio electrónico de la compañía disponemos de la tabla `PRICES` que refleja el precio final (PVP) y la tarifa que aplica a un producto de una cadena entre unas fechas determinadas. A continuación, se muestra un ejemplo de la tabla con los campos relevantes:

| BRAND_ID | START_DATE          | END_DATE            | PRICE_LIST  | PRODUCT_ID  | PRIORITY  | PRICE  | CURR  |
|----------|---------------------|---------------------|-------------|-------------|-----------|--------|-------|
| 1        | 2020-06-14-00.00.00 | 2020-12-31-23.59.59 | 1           | 35455       | 0         | 35.50  | EUR   |
| 1        | 2020-06-14-15.00.00 | 2020-06-14-18.30.00 | 2           | 35455       | 1         | 25.45  | EUR   |
| 1        | 2020-06-15-00.00.00 | 2020-06-15-11.00.00 | 3           | 35455       | 1         | 30.50  | EUR   |
| 1        | 2020-06-15-16.00.00 | 2020-12-31-23.59.59 | 4           | 35455       | 1         | 38.95  | EUR   |

Información de cada uno de los campos de la tabla `PRICES`:
- BRAND_ID: Foreign Key de la cadena del grupo (1 = ZARA).
- START_DATE: Fecha de inicio en el que aplica el precio tarifa indicado.
- END_DATE: Fecha de fin en el que aplica el precio tarifa indicado.
- PRICE_LIST: Identificador de la tarifa de precios aplicable.
- PRODUCT_ID: Identificador del código de producto.
- PRIORITY: Desambiguador de aplicación de precios. Si dos tarifas coinciden en un rago de fechas se aplica la de mayor prioridad (mayor valor numérico).
- PRICE: Precio final de venta.
- CURR: ISO de la moneda.

### Se pide:

Construir una aplicación/servicio en SpringBoot que provea una end point rest de consulta  tal que:

Acepte como parámetros de entrada:
- Fecha de aplicación, con el siguiente formato '2020-12-31 23:59:59'.
- Identificador de producto, formato numérico entero.
- Identificador de cadena, formato numérico entero.

Devuelva como datos de salida:
- Identificador de producto.
- Identificador de cadena.
- Tarifa a aplicar.
- Fechas de aplicación.
- Precio final a aplicar.

Se debe utilizar una base de datos en memoria (tipo H2) e inicializar con los datos del ejemplo, (se pueden cambiar el nombre de los campos y añadir otros nuevos si se quiere, elegir el tipo de dato que se considere adecuado para los mismos).

Desarrollar unos test al endpoint rest que  validen las siguientes peticiones al servicio con los datos del ejemplo:

-          Test 1: petición a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA)
-          Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)
-          Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)
-          Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA)
-          Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)


### Se valorará:
- Diseño y construcción del servicio.
- Calidad de Código.
- Resultados correctos en los test.
