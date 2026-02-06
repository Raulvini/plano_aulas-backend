# Estágio 1: Build

# Etapa 1: Build
FROM gradle:8.8-jdk21 AS build
WORKDIR /app

# Copia todos os arquivos do projeto
COPY . .

# Dá permissão de execução pro gradlew
RUN chmod +x gradlew

# Compila e gera o .jar
RUN ./gradlew clean build -x test

# Etapa 2: Runtime
FROM eclipse-temurin:21-jdk
WORKDIR /app

# Copia o JAR gerado na etapa anterior
COPY --from=build /app/build/libs/*.jar app.jar

# Expõe a porta padrão do Spring Boot
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]