# Askend test exercise - backend

Prerequisite: Java21 and optionally Docker

Build:
```
./gradlew clean build
```

Run with Postgres:
```
docker-compose up -d
./gradlew bootRun --args='--spring.profiles.active=local-postgres'
```

Run with H2:
```
./gradlew bootRun --args='--spring.profiles.active=local-h2'
```

Application will be at http://localhost:8080
