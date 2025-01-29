// S3Config.java
package br.com.alfac.videostudio.infra.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.services.s3.S3Configuration;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

import java.net.URI;

@Configuration
public class S3Config {

    @Value("${aws.accessKey}")
    private String accessKey;

    @Value("${aws.secretKey}")
    private String secretKey;

    @Value("${cloud.aws.region.static}")
    private String awsRegion;

    //@Value("${cloud.aws.s3.endpoint}")
    //private String s3Endpoint;

    @Bean
    public S3Client s3Client() {
        return S3Client.builder()
                .region(Region.of(awsRegion))
                .credentialsProvider(getCredentialsProvider())
                //.endpointOverride(URI.create(s3Endpoint))
                //.serviceConfiguration(S3Configuration.builder().pathStyleAccessEnabled(true).build())
                .build();
    }

    @Profile("local")
    @Bean
    public S3Presigner s3PresignerLocal(S3Client s3Client) {
        return S3Presigner.builder()
                .endpointOverride(s3Client.serviceClientConfiguration().endpointOverride().get())
                .region(s3Client.serviceClientConfiguration().region())
                .credentialsProvider(getCredentialsProvider())
                .serviceConfiguration(S3Configuration.builder()
                        .pathStyleAccessEnabled(true) // Força o uso de path-style
                        .build())
                .build();
    }

    @Profile("!local")
    @Bean
    public S3Presigner s3Presigner(S3Client s3Client) {
        return S3Presigner.builder()
                //.endpointOverride(s3Client.serviceClientConfiguration().endpointOverride().get())
                .region(s3Client.serviceClientConfiguration().region())
                .credentialsProvider(getCredentialsProvider())
                .build();
    }

    private StaticCredentialsProvider getCredentialsProvider() {
        return StaticCredentialsProvider.create(
                AwsBasicCredentials.create(accessKey, secretKey));
    }
}