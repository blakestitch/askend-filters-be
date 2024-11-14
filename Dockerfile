FROM azul/zulu-openjdk-alpine:21.0.1-21.30.15

COPY . .
RUN ./gradlew clean build

EXPOSE 8080

ENTRYPOINT java -jar build/libs/askend-filters-be-1.0.jar