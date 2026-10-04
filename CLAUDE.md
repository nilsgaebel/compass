# Compass

Persönliches Dashboard im Browser (Sport, Tech/KI-News, Finanzen), mobil und am PC.
Portfolio-Projekt zum Lernen von Cloud und KI. Zeitbudget: 2–4 h/Woche.

## Arbeitsweise

- Antworte auf Deutsch, direkt und praxisnah.
- Minimaler Code: kleinste Änderung, die funktioniert. Keine überflüssigen Dateien,
  Abstraktionen oder Planungsdokumente. Kommentare einzeilig und knapp.
- Erkläre bei neuen Konzepten (React, Kubernetes) kurz, was neu ist und warum.
- Eine Aufgabe = ein Branch = ein Pull Request. `main` bleibt lauffähig.
- Pro Endpunkt mindestens ein Test.
- Nichts löschen, umbenennen oder pushen ohne ausdrückliche Freigabe. Ausnahme:
  Gemergte Branches dürfen lokal gelöscht werden, GitHub löscht sie automatisch.
- Harte Randbedingung: Alles, was Compass im Betrieb braucht, ist kostenlos und
  möglichst Open Source. Keine kostenpflichtigen Cloud-Dienste.

## Struktur

```
backend/              Quarkus 3, Java 21, Maven
  src/main/webui/     React-Frontend über Quinoa (geplant)
ai-service/           Python, FastAPI, News-Clustering (geplant)
deploy/               Kustomize mit base/ und overlays/local, ArgoCD (geplant)
```

## Backend

Package `com.compass.<modul>`, bisher nur `sports`. Jedes Modul hat
dieselben Schichten, Abhängigkeiten zeigen nach innen:

- `rest/` – HTTP-Grenze, dünn, validiert und delegiert
- `service/` – Geschäftslogik, orchestriert Client und Mapper
- `client/` – typisierter REST-Client, DTOs der externen API, Mapper
- `domain/` – unveränderliche Records, kennen weder HTTP noch die externe API

Der Mapper ist der Anti-Corruption-Layer: Nur er kennt die Feldnamen des Anbieters.
Neuer Endpunkt: Domain-Record → DTO → Client-Methode → Mapper → Service → Resource → Test.

Datenquelle Sport: TheSportsDB v1, freier Key `3`, 30 Anfragen/Minute.
`eventslast.php` liefert im freien Tarif teils nur Heimspiele, Listen sind gedeckelt.

Befehle (in `backend/`):

- `mvn quarkus:dev` – Dev-Modus, Swagger UI unter `/q/swagger-ui`
- `mvn verify` – Build und Tests

## Frontend (geplant)

React, TypeScript, Vite, Tailwind, shadcn/ui, TanStack Query, React Router, Recharts,
vite-plugin-pwa. Mobil zuerst: unten Navigation, am PC Seitenleiste. Startseite
„Heute“ mit Kacheln. Erst Clickdummy mit Mock-Daten, dann Anschluss ans Backend.

## Betrieb (geplant)

k3d lokal, Kustomize, ArgoCD, Sealed Secrets, GitHub Actions mit Images auf ghcr.io.
Später k3s auf einer VM. Konfiguration nur über Umgebungsvariablen, Daten nur in
PostgreSQL, jeder Dienst mit Health-Probes und Ressourcen-Limits.

## Vorbereitung auf mehrere Nutzer

Nutzerdaten (Favoriten, Watchlist) hängen an einer Nutzer-ID. Kacheln sind
eigenständige Module. Feeds und Themen sind Daten, kein Code.

## Etappen

1. Sport-Kachel mit React-Frontend, Favoriten in PostgreSQL
2. Kubernetes und GitOps
3. KI-Service: Clustering von Sport- und Tech/KI-Headlines
4. Finanz-Kachel
5. Cloud-VM, Domain, TLS, Login
