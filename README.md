# Interfaz de Restaurante

Este proyecto es una aplicación de gestión de restaurante desarrollada en Java Swing, utilizando JDBC con MySQL para la gestión de datos.

La aplicación permite a los usuarios (camareros y cocineros) gestionar mesas y pedidos eficientemente a través de una interfaz gráfica intuitiva. 

Esta aplicación incluye soporte multilenguaje (catalán, español e inglés), cambio de temas visuales mediante FlatLaf (Gestor de temas), y un sistema de logs. 

Los camareros pueden gestionar el estado de las mesas y procesar pedidos y pagos, mientras que los cocineros pueden monitorizar y actualizar el estado de los pedidos en la cocina. 

La aplicación garantiza una experiencia de usuario fluida y una gestión eficaz del flujo de trabajo en restaurantes.

## Características

- **Validación de licencia**: Un sistema para validar las licencias en formato **csv**, las licencias se encuentran en  **```llicencies/llicenciaX.csv```**
- **Gestión de Usuarios**: Inicio de sesión y registro con validación de nombre de usuario y contraseña.
- **Multilenguaje**: Soporte para Catalán, Castellano e Inglés.
- **Interfaz de usuario**: Utiliza FlatLaf para cambios de tema y un sistema de pestañas para la navegación.
- **Gestión de Mesas**: Visualización de mesas para camareros con estados (libre/ocupado) y gestión de pedidos.
- **Base de Datos**: Uso de MySQL para almacenar credenciales de usuarios, menús y pedidos en formato JSON y gestionar el estado de las mesas.

## Instalación y Configuración

### Prerrequisitos

- Base de datos MYSQL o MariaDB 8.2.12 o superior
- Java JDK versión 21.0.2 o superior
- Apache Netbeans IDE 16

### Instalación

1. Clona el repositorio:
   ```bash
   git clone https://github.com/BakerIsCoding/interfaz-restaurante.git
   ```
2. Importa el proyecto en tu IDE (se recomienda **Netbeans 16** o superior)
3. Configura tu servidor **MySQL** y asegúrate de que esté en ejecución.
4. Crea la base de datos necesaria ejecutando el script SQL proporcionado en **```'db/canpedro.sql'```**.

## Configuración
- Configura las propiedades de conexión a la base de datos en el archivo **DatabaseController.java**.
    - **URL:** La url de la base de datos. (Ejemplo: **jdbc:mysql://localhost:3306/canpedro**)
    - **USER:** El usuario de la base de datos, debe tener permisos para **seleccionar, editar y eliminar**. (Ejemplo: **admin**)
    - **PASSWORD:** La contraseña para acceder a la base de datos (Ejemplo: **f@VxoYbq(/0Qo2b]**)


## Uso
### Iniciar la Aplicación
Para iniciar la aplicación, ejecuta el archivo Main.java que se encuentra en el paquete **com.groupf.java.swing.m7**

### Tutorial de Uso
#### Camarero

1. Introduce el archivo de licencia.
2. Inicia sesión o regístrate.
3. Configurar el lenguaje o tema **(Opcional)**
4. Elige un menú y observa el estado de las mesas.
5. Selecciona una mesa, añade platos y envía el pedido a la cocina.
6. Espera a que el pedido esté listo para realizar el pago.

#### Cocinero
1. Introduce el archivo de licencia.
2. Inicia sesión o regístrate.
3. Configurar el lenguaje o tema **(Opcional)**
4. Revisa los pedidos entrantes y cambia su estado a "En preparación" o "Listo".
5. Gestiona varios pedidos al mismo tiempo utilizando los timers proporcionados.

## Autores

- Cramcat639 - [GitHub](https://github.com/Cramcat639)
- EdNuGa - [GitHub](https://github.com/EdNuGa)
- BakerIsCoding - [GitHub](https://github.com/BakerIsCoding)

## Licencia
Este proyecto está bajo la Licencia ```[CC BY 4.0]```. 

