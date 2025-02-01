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
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3Configuration;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

import java.net.URI;

@Configuration
public class S3Config {

    @Value("${aws.accessKey}")
    private String accessKey;

    @Value("${aws.secretKey}")
    private String secretKey;

    @Value("${aws.sessionToken:}")
    private String sessionToken;

    @Value("${cloud.aws.region.static}")
    private String awsRegion;

    @Value("${cloud.aws.s3.endpoint}")
    private String s3Endpoint;

    @Bean
    @Profile("local")
    public S3Client s3ClientLocal() {
        return S3Client.builder()
                .region(Region.of(awsRegion))
                .credentialsProvider(getCredentialsProvider("local"))
                .endpointOverride(URI.create(s3Endpoint))
                .serviceConfiguration(S3Configuration.builder().pathStyleAccessEnabled(true).build())
                .build();
    }

    @Bean
    @Profile("prod")
    public S3Client s3ClientProd() {
        return S3Client.builder()
                .region(Region.of(awsRegion))
                .credentialsProvider(getCredentialsProvider("prod"))
                .build();
    }

    @Bean
    @Profile("test")
    public S3Client s3ClientTest() {
        return Mockito.mock(S3Client.class);
    }

    @Bean
    @Profile("local")
    public S3Presigner s3PresignerLocal() {
        return S3Presigner.builder()
                .endpointOverride(URI.create(s3Endpoint))
                .region(Region.of(awsRegion))
                .credentialsProvider(getCredentialsProvider("local"))
                .serviceConfiguration(S3Configuration.builder()
                        .pathStyleAccessEnabled(true) // Força o uso de path-style
                        .build())
                .build();
    }

    @Bean
    @Profile("prod")
    public S3Presigner s3PresignerProd() {
        return S3Presigner.builder()
                .region(Region.of(awsRegion))
                .credentialsProvider(getCredentialsProvider("prod"))
                .build();
    }

    @Bean
    @Profile("test")
    public S3Presigner s3PresignerTest() {
        return Mockito.mock(S3Presigner.class);
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