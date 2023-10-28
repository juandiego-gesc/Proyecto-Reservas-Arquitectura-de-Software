# Proyecto Reservas
## Integrantes:
- Carlos Farouk Abdalá Rincón
- César Felipe Giraldo Mora
- Juan Diego García Escobar

## ¿Cómo levantar la API con https?

Es importante tener en cuenta esta sección los siguientes términos:
- SSL (Secure Sockets Layer): Tecnología que permite cifrar el tráfico de datos entre un navegador y un sitio web. Esto nos ayuda a verificar la seguridad de la conexión con el sitio al que estamos ingresando.
- TLS (Transpor Layer Security): TLS es una versión actualizada de SSL. Se siguen usando los certificados de seguridad brindados por SSL por estandarización, pero esta es más moderna.
- HTTPS (HyperText Transfer Protocol Secure): Es una versión con implementación de certificados de seguridad del protocolo HTTP.
- PKCS12: Tipo de almacenamiento de claves que combina certificados y claves privadas en un solo archivo.
- RSA: Algoritmo de generación de claves.

Para poder implementar TLS par poder levantar la API con HTTPS, se deben seguir los siguientes pasos:

1. Abrir terminal en el proyecto
2. Ya en el terminal se debe colocar el siguiente comando:
 ```keytool -genkey -alias nombre-del-proyecto -storetype PKCS12 -keyalg RSA -keysize 2048 -keystore ubicacion-del-archivo/nombre-archivo.p12  -validity 365```
. Este comando nos indica además de lo explicado al inicio de la sección una tiempo de validez, el nombre del archivo y un tamaño de clave en bits, en este caso 2048.
3. Si el comando está bien digitado, en el mismo terminal nos pedirá crear una contraseña para el certificado y llenar algunos datos necesarios para la generación del mismo.
4. Después de diligenciar estos datos, se creara el archivo que se usa para almacenar los certificados digitales y claves privadas, por eso mismo, será de tipo p.12. En este caso el archivo se llamará ```keystore.12```.
5. continuación se debe poner en el application.properties los siguiente códgos:
    ``` 
    server.ssl.enabled=true
    server.ssl.key-store=classpath:nombre-archivo.p12
    server.ssl.key-store-password=contrasena-establecida-anteriormente
    server.ssl.key-store-type=tipo-de-almacenamiento
    server.ssl.key-alias=nombre-del-proyecto
    ```
   Para el caso del proyecto:
    ``` 
    server.ssl.enabled=true
    server.ssl.key-store=classpath:key_store.p12
    server.ssl.key-store-password=AppReservas
    server.ssl.key-store-type=PKCS12
    server.ssl.key-alias=AppReservasKeyStore
    ```
6. Seguido de esto, debemos generar el certificado, esto lo haremos con los siguientes comandos:
    ```keytool -exportcert -keystore  ubicacion-del-archivo/nombre-archivo.p12 -storepass contraseña -alias nombre-del-proyecto -rfc -file  ubicacion-del-archivo/nombre-certificado.pem```
7. Debemos configurar el certificado para establecer un trustStore con nuestro keyStore, esto se hace de la siguiente manera:
   ```keytool -import -file ubicacion-del-archivo/nombre-certificado.pem` -alias nombre-del-proyecto -keystore ubicacion-del-archivo/nombre-de-truststore.jks```
8. Por último debemos colocar la informacion tanto del certificado como del TrustStore en el application properties, a continuación, el ejemplo con como se hizo en el proyecto
    ```
    server.ssl.trust-store=classpath:AppReservasTrustStore.jks
    server.ssl.trust-store-password=AppReservas
    server.ssl.trust-store-type=JKS
    server.ssl.trust-certificate=classpath:public-certificate.pem
    server.ssl.trust-certificate-private-key= classpath:public-certificate.pem
    ```
   
Es importante recordar que en el application properties de Tests, es necesario poner únicamente este comando:
```server.ssl.enabled=false```
De esta manera, el ssl no será tenido en cuenta para el entorno de pruebas.

Después de estos pasos, ya podemos ver la funcionalidad de TLS implementada en el proyecto. Si tratamos de ingresar a algun End Point del proyecto con el protocolo HTTP, el navegador nos mostrará que no se logro establecer la conexión con el sitio. Si le agregamos la "s" para comenzar a usar el protocolo HTTPS, puede que nos deje entrar y nos diga que el sitio no es seguro, o que si tenemos algún antivirus no nos permita ingresar al End Point.

De esta manera podemos corroborar el uso de HTTPS para levantar el API.

## Mutation Test
El proyecto cuenta con la implementación de PiTest lo cual es un servicio para hacer Mutation test. Los resultados de este se pueden ver en un archivo comprimido justo antes de los resultados de Jacoco.
El mutaton


## Objetivos del Trabajo:
El objetivo de este trabajo es aplicar lo aprendido en la materia en un contexto real. En este proyecto, hemos desarrollado una API que proporciona funciones esenciales para empresas de servicios, como salones de belleza, restaurantes o centros médicos. Esta API permite a los clientes reservar citas y gestionar horarios disponibles de manera eficiente.

# Cómo Ejecutar el Proyecto

 

Para ejecutar el proyecto con éxito, siga estos pasos:
 

1. **Ejecutar el Proyecto:** Ejecute el proyecto ejecutando el comando 'docker compose up' en el terminal.

 

2. **Acceder al Swagger:** Después de ejecutar el proyecto, vaya a la dirección `https://localhost:8080` en su navegador web.

 

3. **Utilice Swagger:** Utilice Swagger para explorar las diferentes rutas y endpoints disponibles en el proyecto. Apoyarse del postman, todos disponibles en Doc, Swagger proporcionará documentación  fácil de usar para interactuar con la API del proyecto.


Como probar el proyecto:
1. Utilizar el archivo de postman adjunto
2. Asegurarse de que en la sección de Basic Auth, el usuario sea "user" y la contraseña "user"

*Para hacer una prueba rapida, utilizar las funciones relacionadas con task dado que no tienen pre requisitos de informacion.

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


