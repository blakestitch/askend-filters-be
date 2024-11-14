# Askend test exercise - backend

Prerequisite: Java21 or Docker

## Build

```
./gradlew clean build
```

## Run with H2

```
./gradlew bootRun --args='--spring.profiles.active=local-h2'
```

## Run with Postgres

```
docker-compose up -d
```

Application will be at http://localhost:8080
