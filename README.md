# Sports Data Service (Quarkus)

A small Quarkus REST service that exposes sports team data, backed by an
external provider (TheSportsDB). Built as a deliberately clean, layered service
to practice Quarkus, sound OO design, and a CI/CD pipeline that produces a
container image.

## Architecture

The code is split into layers so each has one job and the dependencies point
inward, toward the domain:

```
rest/      TeamResource        HTTP boundary. Thin. Validates input, shapes responses.
service/   TeamService         Business logic. Orchestrates client + mapper.
client/    SportsApiClient     Typed REST client for the external API.
           TheSportsDbResponse DTOs mirroring the provider's raw payload.
           TeamMapper          Anti-corruption layer: provider payload -> domain.
domain/    Team                Immutable domain record. Knows nothing about HTTP or the provider.
```

The key idea is the **anti-corruption layer**: the rest of the app never sees
the provider's field names (`strTeam`, `idTeam`, ...). `TeamMapper` is the only
place that changes if the provider changes or is swapped out.

## Run locally

Dev mode (live reload, Swagger UI at http://localhost:8080/q/swagger-ui):

```bash
mvn quarkus:dev
```

Try it:

```bash
curl "http://localhost:8080/teams/search?name=Arsenal"
```

## Test

```bash
mvn verify
```

## Container image

```bash
docker build -f src/main/docker/Dockerfile.jvm -t sports-quarkus-service:latest .
docker run -p 8080:8080 sports-quarkus-service:latest
```

## CI

`.github/workflows/ci.yml` runs on every push/PR to `main`:

1. **Build & Test** — `mvn verify` on JDK 21.
2. **Container Image** — builds the image (only if tests pass).

Free on public repositories via GitHub Actions.

## Roadmap

- [ ] Push image to GitHub Container Registry (ghcr.io)
- [ ] Deploy to a local Kubernetes cluster (k3d / kind)
- [ ] Historize fetched data into Postgres
- [ ] Add the Python AI service (embeddings + short-text clustering) behind this gateway
