# E-Commerce Backend - Spring Boot + MongoDB + JWT

![image](https://github.com/user-attachments/assets/940619ba-7d57-4495-84b3-4edb7c97710e)

Este proyecto es un backend completo para una plataforma de comercio electrónico desarrollado con:

- **Java 17**

- **Spring Boot 3**

- **MongoDB (MongoDB Atlas)**

- **Spring Security + JWT**

- **Swagger UI (documentación interactiva)**

## Funcionalidades principales

🔐 Registro e inicio de sesión de usuarios con JWT

🛍️ CRUD de productos

🛒 Carrito de compras persistente por usuario

📦 Procesamiento de pedidos

📜 Historial de órdenes por usuario

🧠 Validación automática de stock

🧪 Swagger UI para probar la API visualmente

### Requisitos Previos
Para ejecutar este proyecto, necesitarás tener instalado:

- Java JDK 8 o superior.
- Un IDE de Java como IntelliJ IDEA, Eclipse.
- Maven para manejar las dependencias 
- Un navegador web para interactuar con el servidor.

### Instalación 

1. Tener instalado Git en tu maquina local 
2. Elegir una carpeta en donde guardes tu proyecto
3. abrir la terminal de GIT --> mediante el clik derecho seleccionas Git bash here
4. Clona el repositorio en tu máquina local:
   ```bash
   git clone https://github.com/andreec2/PruebaTecnicaSemillero.git
   ```
5. Abre el proyecto con tu IDE favorito o navega hasta el directorio del proyecto 
6. Desde la terminal  para compilar el proyecto ejecuta:

   ```bash
   mvn clean install
   ```
7. compila el proyecto  

   ```bash
    mvn clean package
   ```
8. Corra el servidor en la clase httpServer "main" o ejecute el siguiente comando desde consola
   
      ```bash
    java -cp target/classes com.prueba.semillero.SemilleroApplication
   ```

9. Abre tu navegador y ve a:
   
      ```bash
    http://localhost:8080
   ```

## Endpoints disponibles

ProductController

![image](https://github.com/user-attachments/assets/64753b4f-1bc2-4883-b37a-8542c26c45a1)

AuthController

![image](https://github.com/user-attachments/assets/eff212fc-5892-4cb9-9520-aba5ac52f685)

UserController

![image](https://github.com/user-attachments/assets/665b4f69-3c77-41c7-83a9-3a53d2b426c7)

OrderController

![image](https://github.com/user-attachments/assets/649e524c-ac21-4db3-b41b-8591238e6347)

CartController

![image](https://github.com/user-attachments/assets/e70866ba-632c-4bea-87cd-d06943db8020)

## Swagger UI

La documentación de la API está disponible en:

   ```bash
   http://localhost:8080/swagger-ui/index.html
   ```

## Built With
* [Maven](https://maven.apache.org/) - Dependency Management

## Authors

* **Andres felipe montes ortiz** - 
* **@andreec2** - 

## Licencia

Este proyecto fue desarrollado con fines académicos/técnicos como parte de una prueba. El uso es libre y educativo.








