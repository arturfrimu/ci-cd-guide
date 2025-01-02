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
# application.yaml
# resources/secrets.properties

```properties
SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/firstdeploy
SPRING_DATASOURCE_USERNAME=root
SPRING_DATASOURCE_PASSWORD=root
```