# ===============================
# Etapa 1: Build do JAR com Maven
# ===============================
FROM maven:3.9.9-eclipse-temurin-21 AS builder

# Diretório de trabalho
WORKDIR /app

# Copia o pom.xml e baixa dependências (cache eficiente)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia o código-fonte e gera o JAR
COPY src ./src
RUN mvn clean package -DskipTests

# ===============================
# Etapa 2: Imagem final para rodar o app
# ===============================
FROM eclipse-temurin:21-jdk

# Diretório de trabalho
WORKDIR /app

# Copia apenas o JAR gerado
COPY --from=builder /app/target/*.jar app.jar

# Expõe a porta do Spring Boot
EXPOSE 8080

# Ponto de entrada
ENTRYPOINT ["java", "-jar", "app.jar"]
