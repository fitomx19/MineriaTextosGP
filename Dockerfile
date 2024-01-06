# Usa una imagen de OpenJDK 21 como base
FROM openjdk:21

# Directorio de trabajo dentro del contenedor
WORKDIR /MineriaDeDatos


# Copia el archivo JAR de la aplicación en el contenedor
COPY ./app.jar /MineriaDeDatos/app.jar


# Comando para ejecutar la aplicación Spring Boot
CMD ["java", "-jar", "/MineriaDeDatos/app.jar"]


#COMANDO UTILES -->

#docker build -t mineria-datos   .
#docker run -p 8081:8080 mineria-datos
