package br.com.alfac.videostudio.infra.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.awspring.cloud.sns.core.SnsTemplate;
import software.amazon.awssdk.auth.credentials.AwsSessionCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;

@Configuration
public class SnsConfig {

    @Value("${aws.accessKey}")
    private String accessKey;

    @Value("${aws.secretKey}")
    private String secretKey;

    @Value("${aws.sessionToken}")
    private String sessionToken;

    @Value("${cloud.aws.region.static}")
    private String awsRegion;

    @Bean
    public SnsClient snsClient() {
        return SnsClient.builder()
                .region(Region.of(awsRegion))
                // .endpointOverride(URI.create("arn:aws:sns:us-east-1:000687245264:envia_email_erro_processamento"))
                // // Endpoint do LocalStack
                .credentialsProvider(getCredentialsProvider())
                .build();
    }

    @Bean
    public SnsTemplate snsTemplate(SnsClient snsClient) {
        return new SnsTemplate(snsClient);
    }

    private StaticCredentialsProvider getCredentialsProvider() {
        return StaticCredentialsProvider.create(
                AwsSessionCredentials.create(accessKey, secretKey, sessionToken));
    }

}
