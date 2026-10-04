# Compass Backend (Quarkus)

REST service behind Compass. Currently serves sports data from TheSportsDB.

## Architecture

Layers per module (`com.compass.sports`), dependencies point inward:

```
rest/      HTTP boundary. Thin. Validates input, shapes responses.
service/   Business logic. Orchestrates client + mapper.
client/    Typed REST client, provider DTOs, and SportsMapper.
domain/    Immutable records. Know nothing about HTTP or the provider.
```

`SportsMapper` is the anti-corruption layer: the only place that knows the
provider's field names (`strTeam`, `idTeam`, ...).

## Run

```bash
mvn quarkus:dev
```

Swagger UI: http://localhost:8080/q/swagger-ui

```bash
curl "http://localhost:8080/teams/search?name=Arsenal"
```

## Test

```bash
mvn verify
```

## Container image

```bash
mvn package -DskipTests
docker build -f src/main/docker/Dockerfile.jvm -t compass-backend .
docker run -p 8080:8080 compass-backend
```
