#!/bin/sh

set -e  # Termina o script se qualquer comando falhar

# Defina as credenciais AWS
export AWS_ACCESS_KEY_ID=test
export AWS_SECRET_ACCESS_KEY=test
export AWS_DEFAULT_REGION=us-east-1

echo "Criando bucket principal"
aws s3 mb s3://hackathon-video-studio-bucket --endpoint-url=http://localhost:4566 || { echo "Falha ao criar o bucket principal"; exit 1; }

echo "Criando pastas dentro do bucket"
aws s3api put-object --bucket hackathon-video-studio-bucket --key videos/ --endpoint-url=http://localhost:4566 || { echo "Falha ao criar a pasta videos"; exit 1; }
aws s3api put-object --bucket hackathon-video-studio-bucket --key zip/ --endpoint-url=http://localhost:4566 || { echo "Falha ao criar a pasta zip"; exit 1; }

echo "Criando filas"
aws --endpoint-url=http://localhost:4566 sqs create-queue --queue-name update_processing_status || { echo "Falha ao criar a fila update_processing_status"; exit 1; }
aws --endpoint-url=http://localhost:4566 sqs create-queue --queue-name videos_to_process || { echo "Falha ao criar a fila videos_to_process"; exit 1; }

echo "Done."