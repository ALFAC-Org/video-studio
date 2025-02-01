# Use a imagem oficial do LocalStack
FROM localstack/localstack:latest

# Defina variáveis de ambiente para configurar o LocalStack
ENV SERVICES=s3,sqs,sns
ENV DEFAULT_REGION=us-east-1

# Exponha as portas necessárias
EXPOSE 4566
EXPOSE 4571

# Copie o script de configuração para o container
COPY local/setup.sh /usr/local/bin/setup.sh

# Copie o script wait-for-it para o container
COPY local/wait-for-it.sh /usr/local/bin/wait-for-it.sh

# Torne os scripts executáveis
RUN chmod +x /usr/local/bin/setup.sh /usr/local/bin/wait-for-it.sh

# Comando para iniciar o LocalStack e executar o script de configuração
CMD ["sh", "-c", "localstack start -d && /usr/local/bin/wait-for-it.sh localhost:4566 -- /usr/local/bin/setup.sh && tail -f /dev/null"]
