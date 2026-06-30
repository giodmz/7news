# Build da aplicação (compilação com Maven)
FROM maven:3.9.6-eclipse-temurin-21-alpine AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Execução (imagem final leve apenas com JRE)
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copie o jar gerado no estágio anterior (ajuste o nome do seu jar se necessário)
COPY --from=build /app/target/*.jar mail.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "mail.jar"]
