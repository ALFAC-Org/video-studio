package br.com.alfac.videostudio.infra.config;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.AwsSessionCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.GetQueueUrlRequest;
import software.amazon.awssdk.services.sqs.model.GetQueueUrlResponse;

import java.net.URI;
import java.util.concurrent.CompletableFuture;

@Configuration
public class SQSConfig {

    @Value("${aws.accessKey}")
    private String accessKey;

    @Value("${aws.secretKey}")
    private String secretKey;

    @Value("${aws.sessionToken:}")
    private String sessionToken;

    @Value("${cloud.aws.region.static}")
    private String awsRegion;

    @Value("${cloud.aws.sqs.endpoint}")
    private String sqsEndpoint;

    @Bean
    @Profile("local")
    public SqsClient sqsClientLocal() {
        return SqsClient.builder()
                .region(Region.of(awsRegion))
                .credentialsProvider(getCredentialsProvider("local"))
                .endpointOverride(URI.create(sqsEndpoint))
                .build();
    }

    @Bean
    @Profile("prod")
    public SqsClient sqsClientProd() {
        return SqsClient.builder()
                .region(Region.of(awsRegion))
                .credentialsProvider(getCredentialsProvider("prod"))
                .build();
    }

    @Bean
    @Profile("test")
    public SqsClient sqsClientTest() {
        return Mockito.mock(SqsClient.class);
    }

    @Bean
    @Primary
    @Profile("local")
    public SqsAsyncClient sqsAsyncClientLocal() {
        return SqsAsyncClient.builder()
                .region(Region.of(awsRegion))
                .credentialsProvider(getCredentialsProvider("local"))
                .endpointOverride(URI.create(sqsEndpoint))
                .build();
    }

    @Bean
    @Primary
    @Profile("prod")
    public SqsAsyncClient sqsAsyncClientProd() {
        return SqsAsyncClient.builder()
                .region(Region.of(awsRegion))
                .credentialsProvider(getCredentialsProvider("prod"))
                .build();
    }

    @Bean
    @Primary
    @Profile("test")
    public SqsAsyncClient sqsAsyncClientTest() {
        SqsAsyncClient mockSqsAsyncClient = Mockito.mock(SqsAsyncClient.class);

        // Configure the mock to return a valid CompletableFuture for getQueueUrl
        GetQueueUrlResponse getQueueUrlResponse = GetQueueUrlResponse.builder()
                .queueUrl("https://sqs.us-east-1.amazonaws.com/123456789012/test-queue")
                .build();
        CompletableFuture<GetQueueUrlResponse> futureGetQueueUrl = CompletableFuture
                .completedFuture(getQueueUrlResponse);

        Mockito.when(mockSqsAsyncClient.getQueueUrl(Mockito.any(GetQueueUrlRequest.class)))
                .thenReturn(futureGetQueueUrl);

        return mockSqsAsyncClient;
    }

    private AwsCredentialsProvider getCredentialsProvider(String profile) {
        if ("prod".equals(profile)) {
            return StaticCredentialsProvider.create(
                    AwsSessionCredentials.create(accessKey, secretKey, sessionToken));
        } else {
            return StaticCredentialsProvider.create(
                    AwsBasicCredentials.create(accessKey, secretKey));
        }
    }
}