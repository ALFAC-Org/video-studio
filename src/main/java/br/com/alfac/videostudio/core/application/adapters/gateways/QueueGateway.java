package br.com.alfac.videostudio.core.application.adapters.gateways;

public interface QueueGateway {

    void sendMessage(String queueName, String message);
    
}
