# Используйте официальный образ OpenJDK для базового слоя
FROM openjdk:17-jdk-slim as build

# Установите рабочий каталог для вашего приложения
WORKDIR /app

# Скопируйте gradle или maven файлы зависимостей в рабочий каталог
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Скопируйте исходный код вашего приложения в Docker образ
COPY src src

# Соберите приложение
RUN ./mvnw package -DskipTests

# Настроим вторую стадию сборки для более легкого образа
FROM openjdk:17-jre-slim
COPY --from=build /app/target/*.jar lootheaven-server-0.0.1-SNAPSHOT.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "/lootheaven-server-0.0.1-SNAPSHOT.jar"]