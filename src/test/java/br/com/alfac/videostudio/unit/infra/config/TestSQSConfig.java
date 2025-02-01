package br.com.alfac.videostudio.unit.infra.config;

import java.util.concurrent.CompletableFuture;

import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import software.amazon.awssdk.services.sqs.SqsAsyncClient;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.GetQueueUrlRequest;
import software.amazon.awssdk.services.sqs.model.GetQueueUrlResponse;

@TestConfiguration
@Profile("test")
public class TestSQSConfig {

  @Bean
  @Primary
  public SqsClient sqsClient() {
    return Mockito.mock(SqsClient.class);
  }

  @Bean
  @Primary
  public SqsAsyncClient sqsAsyncClient() {
    SqsAsyncClient mockSqsAsyncClient = Mockito.mock(SqsAsyncClient.class);

    // Configure the mock to return a valid CompletableFuture
    GetQueueUrlResponse getQueueUrlResponse = GetQueueUrlResponse.builder()
        .queueUrl("https://sqs.us-east-1.amazonaws.com/123456789012/test-queue")
        .build();
    CompletableFuture<GetQueueUrlResponse> future = CompletableFuture.completedFuture(getQueueUrlResponse);

    Mockito.when(mockSqsAsyncClient.getQueueUrl(Mockito.any(GetQueueUrlRequest.class)))
        .thenReturn(future);

    return mockSqsAsyncClient;
  }
}
