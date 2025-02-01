## Rodar antes de executar a aplicação

```bash
docker compose up --build
```

## para rodar a aplicação

```bash
docker start $(docker ps -a -q --filter "name=localstack") && aws s3 mb s3://nome-do-seu-bucket --endpoint-url=http://localhost:4566 && aws --endpoint-url=http://localhost:4566/ sqs create-queue --queue-name queue-video-to-process  && aws --endpoint-url=http://localhost:4566 sns create-topic --name queue-notification-error-processing
```

```
docker run --name mysql-container -e MYSQL_ROOT_PASSWORD=videodbpass -e MYSQL_PASSWORD=videodbpass -e MYSQL_DATABASE=videodb -e MYSQL_USER=videodbuser -p 3306:3306 -d mysql
```

## Sobe o LocalStack via Docker

```bash
docker run -p 4566:4566 -p 4571:4571 --name localstack-container carlohcs/localstack:latest
```

## Verifica se as filas estão configuradas

```bash
aws --endpoint-url=http://localhost:4566 sqs list-queues
```

Deve retornar:

```json
{
    "QueueUrls": [
        "http://sqs.us-east-1.localhost.localstack.cloud:4566/000000000000/update_processing_status",
        "http://sqs.us-east-1.localhost.localstack.cloud:4566/000000000000/videos_to_process"
    ]
}
```