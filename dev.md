# para rodar a aplicação

```bash
docker start $(docker ps -a -q --filter "name=localstack") && aws s3 mb s3://nome-do-seu-bucket --endpoint-url=http://localhost:4566 && aws --endpoint-url=http://localhost:4566/ sqs create-queue --queue-name queue-video-to-process  && aws --endpoint-url=http://localhost:4566 sns create-topic --name queue-notification-error-processing
```