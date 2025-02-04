# DEV Experience - Comandos/fluxos úteis aos desenvolvedores

## Localmente (modo easy)

### Rodar antes de executar a aplicação

1. Entre na pasta `local`

```bash
docker compose up --build
```

2. Depois é só rodar a aplicação via VSCode/IntelliJ ou terminal.

## Localmente (modo hard)

### Rodar antes de executar a aplicação

**LocalStack**

```bash
docker start $(docker ps -a -q --filter "name=localstack") && aws s3 mb s3://hackathon-video-studio-bucket --endpoint-url=http://localhost:4566 || { echo "Falha ao criar o bucket principal"; exit 1; } && aws s3api put-object --bucket hackathon-video-studio-bucket --key videos/ --endpoint-url=http://localhost:4566 || { echo "Falha ao criar a pasta videos"; exit 1; }
aws s3api put-object --bucket hackathon-video-studio-bucket --key zip/ --endpoint-url=http://localhost:4566 || { echo "Falha ao criar a pasta zip"; exit 1; } && aws --endpoint-url=http://localhost:4566 sqs create-queue --queue-name update_processing_status || { echo "Falha ao criar a fila update_processing_status"; exit 1; }
aws --endpoint-url=http://localhost:4566 sqs create-queue --queue-name videos_to_process || { echo "Falha ao criar a fila videos_to_process"; exit 1; }
```

**Banco de dados**

```bash
docker run --name mysql-container -e MYSQL_ROOT_PASSWORD=videodbpass -e MYSQL_PASSWORD=videodbpass -e MYSQL_DATABASE=videodb -e MYSQL_USER=videodbuser -p 3306:3306 -d mysql
```

Se quiser acessar o localstack localmente para ver os logs:

```bash
docker run -p 4566:4566 -p 4571:4571 --name localstack-container carlohcs/localstack:latest
```

#### Verifique se as filas estão corretamente configuradas

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