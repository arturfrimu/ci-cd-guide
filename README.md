```shell
docker build -t first-deploy . 
```

```shell
docker tag first-deploy arturfrimu/firstdeploy:1
```

```shell
docker push arturfrimu/firstdeploy:1
```

# docker-compose.yaml

```yaml
version: '3.8'

services:
  postgres:
    image: postgres:13
    container_name: firstdeploy
    environment:
      POSTGRES_DB: firstdeploy
      POSTGRES_USER: root
      POSTGRES_PASSWORD: root
    ports:
      - "5432:5432"
```

# application.yaml

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/firstdeploy
    username: root
    password: root
    driver-class-name: org.postgresql.Driver
  jpa:
    hibernate:
      ddl-auto: validate
    show-sql: true
    properties:
      hibernate:
        format_sql: true
    database: postgresql
    database-platform: org.hibernate.dialect.PostgreSQLDialect
```

# resources/secrets.properties

```properties
SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/firstdeploy
SPRING_DATASOURCE_USERNAME=root
SPRING_DATASOURCE_PASSWORD=root
```

# application.yaml

```yaml
url: ${SPRING_DATASOURCE_URL}
username: ${SPRING_DATASOURCE_USERNAME}
password: ${SPRING_DATASOURCE_PASSWORD}
```