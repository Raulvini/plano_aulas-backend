# Estágio 1: Build
FROM gradle:8.4-jdk17-alpine AS build
WORKDIR /app

# Copia os arquivos de configuração do Gradle para cache de dependências
COPY build.gradle settings.gradle ./
# Se você usa o wrapper do gradle (pastas gradle/ e arquivo gradlew)
COPY gradle ./gradle
COPY gradlew ./
RUN ./gradlew dependencies --no-daemon

# Copia o código fonte e gera o jar
COPY src ./src
RUN ./gradlew bootJar --no-daemon

# Estágio 2: Runtime
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# O Gradle por padrão gera o jar em build/libs/
# O wildcard *-SNAPSHOT.jar ou *.jar garante que pegue o arquivo correto
COPY --from=build /app/build/libs/*.jar app.jar

# Porta padrão (o Render injetará a variável $PORT)
EXPOSE 8080

ENTRYPOINT ["java", "-Xmx512m", "-jar", "app.jar"]