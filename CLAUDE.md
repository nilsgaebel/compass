# Compass

Personal dashboard in the browser (sports, tech/AI news, finance), for mobile and desktop.
Portfolio project for learning cloud and AI. Time budget: 2–4 h/week.

## Working rules

- Everything in the repo is in English: code, comments, docs, UI text, commit
  messages, and pull requests. Chat replies are in German, direct and practical.
- Minimal code: the smallest change that works. No unnecessary files, abstractions,
  or planning documents. Comments are one line and concise.
- For new concepts (React, Kubernetes), briefly explain what is new and why.
- One task = one branch = one pull request. `main` always stays runnable.
- At least one test per endpoint.
- Do not delete, rename, or push anything without explicit approval. Exception:
  merged branches may be deleted locally; GitHub deletes them automatically.
- Hard constraint: everything Compass needs at runtime is free and preferably
  open source. No paid cloud services.

## Structure

```
backend/              Quarkus 3, Java 21, Maven
  src/main/webui/     React frontend via Quinoa (planned)
ai-service/           Python, FastAPI, news clustering (planned)
deploy/               Kustomize with base/ and overlays/local, ArgoCD (planned)
```

## Backend

Package `com.compass.<module>`, so far only `sports`. Every module has the same
layers, dependencies point inward:

- `rest/` – HTTP boundary, thin, validates and delegates
- `service/` – business logic, orchestrates client and mapper
- `client/` – typed REST client, DTOs of the external API, mapper
- `domain/` – immutable records, know nothing about HTTP or the external API

The mapper is the anti-corruption layer: only it knows the provider's field names.
New endpoint: domain record → DTO → client method → mapper → service → resource → test.

Sports data source: TheSportsDB v1, free key `3`, 30 requests/minute.
On the free tier `eventslast.php` sometimes returns home games only, and lists are capped.

Commands (in `backend/`):

- `mvn quarkus:dev` – dev mode, Swagger UI at `/q/swagger-ui`
- `mvn verify` – build and tests

## Frontend (planned)

React, TypeScript, Vite, Tailwind, shadcn/ui, TanStack Query, React Router, Recharts,
vite-plugin-pwa. Mobile first: bottom navigation, sidebar on desktop. Home page
"Today" with tiles. Click dummy with mock data first, then connect to the backend.

## Operations (planned)

k3d locally, Kustomize, ArgoCD, Sealed Secrets, GitHub Actions with images on ghcr.io.
Later k3s on a VM. Configuration via environment variables only, data in PostgreSQL
only, every service with health probes and resource limits.

## Preparing for multiple users

User data (favorites, watchlist) is tied to a user ID. Tiles are independent
modules. Feeds and topics are data, not code.

## Milestones

1. Sports tile with React frontend, favorites in PostgreSQL
2. Kubernetes and GitOps
3. AI service: clustering of sports and tech/AI headlines
4. Finance tile
5. Cloud VM, domain, TLS, login
