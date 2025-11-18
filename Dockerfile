# ===== Этап 1: Сборка JAR с Gradle =====
FROM eclipse-temurin:25-jdk-alpine AS builder
WORKDIR /build

# 1. Копируем Gradle Wrapper (обязательно!)
COPY gradlew .
COPY gradle ./gradle
COPY build.gradle.kts settings.gradle.kts ./

# 2. (Опционально) Копируем gradle.properties, если есть
# COPY gradle.properties .

# 3. Загружаем зависимости (кэшируем слой)
RUN ./gradlew dependencies --no-daemon || true

# 4. Копируем исходный код
COPY src ./src

# 5. Собираем JAR
RUN ./gradlew bootJar --no-daemon

# ===== Этап 2: Запуск =====
FROM eclipse-temurin:25-jre-alpine
WORKDIR /app

# Копируем только JAR
COPY --from=builder /build/build/libs/*.jar app.jar

EXPOSE 8081

ENV SPRING_PROFILES_ACTIVE=prod \
    JAVA_OPTS="-Xms256m -Xmx512m -XX:+UseZGC"

USER 1001

HEALTHCHECK --interval=30s --timeout=3s --start-period=60s --retries=3 \
  CMD wget --no-verbose --tries=1 --spider http://localhost:8080/actuator/health || exit 1

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]