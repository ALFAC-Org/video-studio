# Use a imagem com JDK e Maven para compilar a aplicacao
FROM maven:3.8.1-openjdk-17-slim AS build

# Define o WORKDIR no container
WORKDIR /app

# Copia o projeto inteiro para o WORKDIR
COPY . .

# Compila o aplicativo com o Maven
RUN mvn clean install -U -DskipTests

# Crie uma imagem baseada na JDK para executar a aplicacao
FROM openjdk:17-slim

ARG PROFILE=prod

# Define o WORKDIR no container
WORKDIR /app

# Copia o JAR da aplicacao para o WORKDIR
COPY --from=build /app/target/*.jar /app/app.jar

ENV AWS_REGION=us-east-1
ENV AWS_DEFAULT_REGION=us-east-1
ENV PROFILE=${PROFILE}

# Executa a aplicacao
CMD ["java", "-Dspring.profiles.active=${PROFILE}", "-Dlogging.level.root=DEBUG", "-jar", "app.jar"]

# COMANDO
# BUILD
# docker buildx build --platform linux/arm64 -f Dockerfile -t carlohcs/video-studio:latest . --build-arg PROFILE=prod --load --no-cache
# RUN
# docker run -p 8080:8080 -e PROFILE=local carlohcs/video-studio:latest
