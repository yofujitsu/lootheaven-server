# Используйте официальный образ OpenJDK для базового слоя
FROM openjdk:17-jdk-slim as build

# Установите рабочий каталог для вашего приложения
WORKDIR /app

# Обновите пакеты и установите необходимые инструменты
RUN apt-get update && apt-get install -y wget unzip

# Скачайте и установите Gradle
RUN wget https://services.gradle.org/distributions/gradle-8.7-bin.zip -P /tmp
RUN unzip -d /opt/gradle /tmp/gradle-8.7-bin.zip
ENV GRADLE_HOME=/opt/gradle/gradle-8.7
ENV PATH=${GRADLE_HOME}/bin:${PATH}

# Скопируйте Gradle wrapper и файлы зависимостей в рабочий каталог, если необходимо
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .

COPY init.sql .

# Сделайте скрипт gradlew исполняемым
RUN chmod +x ./gradlew

# Скопируйте исходный код вашего приложения в Docker образ
COPY src src

# Соберите приложение
RUN ./gradlew build -x test

# Настройте вторую стадию сборки для более легкого образа
FROM openjdk:17-jdk-slim
COPY --from=build /app/build/libs/*.jar lootheaven-server.jar

# Обозначьте, что контейнер будет слушать порт 8082
EXPOSE 8082

# Установите точку входа для запуска приложения
ENTRYPOINT ["java", "-jar", "/lootheaven-server.jar"]
