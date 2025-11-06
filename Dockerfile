# ----- Estágio 1: Build (Java 21) -----
# MUDEI A LINHA 1: Removi o "3.9-" e o "-focal"
FROM maven:3-eclipse-temurin-21 AS build

# Define o diretório de trabalho
WORKDIR /app

# Copia o pom.xml primeiro para aproveitar o cache do Docker
COPY pom.xml .
# Baixa as dependências
RUN mvn dependency:go-offline -DskipTests

# Copia o resto do código-fonte
COPY src ./src
COPY mvnw .
COPY .mvn .mvn
COPY mvnw.cmd .

# Compila o projeto e cria o .jar (pulando os testes)
RUN mvn package -DskipTests

# ----- Estágio 2: Imagem Final (Java 21) -----
# Esta linha está correta
FROM eclipse-temurin:21-jre-alpine

# Define o diretório de trabalho
WORKDIR /app

# Copia SOMENTE o .jar buildado do estágio 1
COPY --from=build /app/target/*.jar app.jar

# Expõe a porta que o Spring vai usar
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]