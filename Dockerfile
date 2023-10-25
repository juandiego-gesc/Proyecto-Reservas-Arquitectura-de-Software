# Imagen base con una distribucion de java que funciona en linux
FROM openjdk:17-oracle

# Crear directorio de trabajo para la aplicacion
COPY build/libs/appreservas-*.jar /app/appreservas.jar

# Comando para ejecutar la aplicacion
CMD ["java", "-jar", "/app/appreservas.jar"]