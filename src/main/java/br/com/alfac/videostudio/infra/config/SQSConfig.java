// S3Config.java
package br.com.alfac.videostudio.infra.config;

import br.com.alfac.videostudio.infra.config.exception.CustomErrorHandler;
//import io.awspring.cloud.sqs.config.SqsListenerConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.util.ErrorHandler;

import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;
import software.amazon.awssdk.services.sqs.SqsClient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URI;

@Configuration
@Profile("prod")
public class SQSConfig {

    @Value("${cloud.aws.region.static}")
    private String awsRegion;

    @Value("${cloud.aws.sqs.endpoint}")
    private String sqsEndpoint;

    AwsCredentialsProvider credentialsProvider;

    @Bean
    public ErrorHandler errorHandler() {
        return new CustomErrorHandler();
    }

    // TODO: Implementar o método configureSqsListeners
    // @Bean
    // public SqsListenerConfigurer sqsListenerConfigurer(ErrorHandler errorHandler)
    // {
    // return new SqsListenerConfigurer() {
    // @Override
    // public void configureSqsListeners(SqsListenerEndpointRegistrar registrar) {
    // registrar.setErrorHandler(errorHandler);
    // }
    // };
    // }

    @Bean
    public SqsClient sqsClient() {
        return SqsClient.builder()
                .region(Region.of(awsRegion))
                .credentialsProvider(credentialsProvider)
                // .endpointOverride(URI.create(sqsEndpoint)) // Use apenas se estiver
                // configurando endpoint personalizado
                .build();
    }

    @Bean
    @Primary
    public SqsAsyncClient sqsAsyncClient() {
        return SqsAsyncClient.builder()
                .region(Region.of(awsRegion))
                // .endpointOverride(URI.create(sqsEndpoint))
                // .region(Region.US_EAST_1)
                .credentialsProvider(credentialsProvider)
                .build();
    }

}