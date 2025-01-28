// S3Config.java
package br.com.alfac.videostudio.infra.config;

import br.com.alfac.videostudio.infra.config.exception.CustomErrorHandler;
//import io.awspring.cloud.sqs.config.SqsListenerConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.util.ErrorHandler;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;
import software.amazon.awssdk.services.sqs.SqsClient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URI;

@Configuration
public class SQSConfig {

    @Value("${aws.accessKey}")
    private String accessKey;

    @Value("${aws.secretKey}")
    private String secretKey;

    @Value("${cloud.aws.region.static}")
    private String awsRegion;

    @Value("${cloud.aws.sqs.endpoint}")
    private String sqsEndpoint;

    @Bean
    public ErrorHandler errorHandler() {
        return new CustomErrorHandler();
    }

    // TODO: Implementar o método configureSqsListeners
//    @Bean
//    public SqsListenerConfigurer sqsListenerConfigurer(ErrorHandler errorHandler) {
//        return new SqsListenerConfigurer() {
//            @Override
//            public void configureSqsListeners(SqsListenerEndpointRegistrar registrar) {
//                registrar.setErrorHandler(errorHandler);
//            }
//        };
//    }

    @Bean
    public SqsClient sqsClient() {
        return SqsClient.builder()
                .region(Region.of(awsRegion))
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(accessKey, secretKey)))
                .endpointOverride(URI.create(sqsEndpoint))  // Use apenas se estiver configurando endpoint personalizado
                .build();
    }

    @Bean
    @Primary
    public SqsAsyncClient sqsAsyncClient() {
        return SqsAsyncClient.builder()
                .endpointOverride(URI.create(sqsEndpoint))
                .region(Region.US_EAST_1)
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(accessKey, secretKey)))
                .build();
    }
}