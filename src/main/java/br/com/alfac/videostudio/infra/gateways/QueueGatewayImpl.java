package br.com.alfac.videostudio.infra.gateways;

import br.com.alfac.videostudio.core.application.adapters.gateways.QueueGateway;

import org.springframework.stereotype.Component;

import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

@Component
public class QueueGatewayImpl implements QueueGateway {

    private final SqsClient sqsClient;

    public QueueGatewayImpl(final SqsClient sqsClient) {
        this.sqsClient = sqsClient;
    }

    public void sendMessage(String queueName, String message) {
        SendMessageRequest sendMessageRequest = SendMessageRequest.builder()
                .queueUrl(queueName)
                .messageBody(message)
                .build();

        sqsClient.sendMessage(sendMessageRequest);
    }

}
