# Proyecto Reservas
## Tiene Mutation Test
El proyecto cuenta con la implementación de PiTest lo cual es un servicio para hacer Mutation test. Los resultados de este se pueden ver en un archivo comprimido justo antes de los resultados de Jacoco.
## Integrantes:
- Carlos Farouk Abdalá Rincón
- César Felipe Giraldo Mora
- Juan Diego García Escobar

## Objetivos del Trabajo:
El objetivo de este trabajo es aplicar lo aprendido en la materia en un contexto real. En este proyecto, hemos desarrollado una API que proporciona funciones esenciales para empresas de servicios, como salones de belleza, restaurantes o centros médicos. Esta API permite a los clientes reservar citas y gestionar horarios disponibles de manera eficiente.

# Cómo Ejecutar el Proyecto

 

Para ejecutar el proyecto con éxito, siga estos pasos:

 

1. **Configurar JDK 17:** Asegúrese de configurar JDK 17 como el SDK para el proyecto. Esto se puede hacer en las propiedades del proyecto.

 

2. **Ejecutar el Proyecto:** Ejecute el proyecto utilizando el IDE de su preferencia.

 

3. **Acceder al Swagger:** Después de ejecutar el proyecto, vaya a la dirección `http://localhost:8080` en su navegador web.

 

4. **Utilice Swagger:** Utilice Swagger para explorar las diferentes rutas y endpoints disponibles en el proyecto. Apoyarse del postman, todos disponibles en Doc, Swagger proporcionará documentación  fácil de usar para interactuar con la API del proyecto.

## Funcionalidades de la API:
A continuación, se describen las principales funcionalidades de nuestra API:

### 1. Configuración de la Franja de Trabajo
La API permite a los administradores configurar la franja de trabajo en la que se podrán realizar reservas. Esto garantiza que las reservas se realicen dentro de un horario predefinido, lo que facilita la gestión de citas.

### 2. Consulta de Disponibilidad
Los clientes pueden utilizar esta función para verificar la disponibilidad de citas en función de la fecha y el horario deseado. Esto ayuda a los clientes a encontrar un horario conveniente para su cita.

### 3. Reserva de Cita
Esta función permite a los clientes programar una cita seleccionando el servicio que desean, eligiendo a un estilista o profesional específico y reservando un horario disponible. La API se encarga de gestionar estas reservas de manera eficiente y evitar conflictos de horarios.

### 4. Cancelación de Cita
En caso de que un cliente necesite cancelar una cita existente, nuestra API proporciona la funcionalidad para hacerlo de manera sencilla. La cancelación de una cita libera automáticamente ese horario para que otros clientes puedan reservarlo.

### 5. Reprogramación de Cita
Los clientes también tienen la opción de reprogramar una cita existente si necesitan cambiar la fecha u hora. Esta función garantiza la flexibilidad para adaptarse a las necesidades cambiantes de los clientes sin complicaciones.


