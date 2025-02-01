package br.com.alfac.videostudio.infra.config;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.AwsSessionCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;

import java.net.URI;

@Configuration
public class SnsConfig {

    @Value("${aws.accessKey}")
    private String accessKey;

    @Value("${aws.secretKey}")
    private String secretKey;

    @Value("${aws.sessionToken:}")
    private String sessionToken;

    @Value("${cloud.aws.region.static}")
    private String awsRegion;

    @Value("${cloud.aws.sns.endpoint}")
    private String snsEndpoint;

    @Bean
    @Profile("local")
    public SnsClient snsClientLocal() {
        return SnsClient.builder()
                .region(Region.of(awsRegion))
                .credentialsProvider(getCredentialsProvider("local"))
                .endpointOverride(URI.create(snsEndpoint))
                .build();
    }

    @Bean
    @Profile("prod")
    public SnsClient snsClientProd() {
        return SnsClient.builder()
                .region(Region.of(awsRegion))
                .credentialsProvider(getCredentialsProvider("prod"))
                .build();
    }

    @Bean
    @Profile("test")
    public SnsClient snsClientTest() {
        return Mockito.mock(SnsClient.class);
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