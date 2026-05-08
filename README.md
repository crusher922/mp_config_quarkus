# Backend e-commerce con Quarkus + MySQL + Docker + Keycloak

## Stack
- Quarkus (RESTEasy + Jackson, Hibernate ORM Panache)
- MySQL 8
- Keycloak (OIDC/JWT)
- Docker / Docker Compose

## Ejecutar local (sin Docker)
```bash
./gradlew quarkusDev
```

## Ejecutar en Docker
```bash
./gradlew clean build -Dquarkus.package.type=fast-jar
docker compose up --build
```

## Endpoints
- `GET /api/health` público
- `GET /api/products` público
- `GET /api/products/{id}` público
- `POST /api/products` rol `admin`
- `GET /api/customers` roles `admin|seller`
- `POST /api/customers` autenticado
- `GET /api/orders` roles `admin|seller`
- `POST /api/orders` roles `admin|seller`

## Recomendaciones de librerías
1. **Keycloak (recomendada)** para gestión de identidad/roles.
2. **MapStruct** para mapear entidades <-> DTOs cuando el dominio crezca.
3. **Flyway** para versionar migraciones SQL en ambientes productivos.
4. **quarkus-smallrye-openapi** para documentación automática.

## Nota de Keycloak
1. Crear realm `ecommerce`.
2. Crear client confidencial `ecommerce-api`.
3. Definir roles `admin` y `seller`.
4. Asignar roles a usuarios y usar el access token Bearer contra los endpoints protegidos.
