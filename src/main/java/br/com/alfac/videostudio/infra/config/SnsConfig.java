package br.com.alfac.videostudio.infra.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.awspring.cloud.sns.core.SnsTemplate;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;

@Configuration
public class SnsConfig {
    @Value("${cloud.aws.region.static}")
    private String awsRegion;

    AwsCredentialsProvider credentialsProvider;

    @Bean
    public SnsClient snsClient() {
        return SnsClient.builder()
                .region(Region.of(awsRegion))
                // .endpointOverride(URI.create("arn:aws:sns:us-east-1:000687245264:envia_email_erro_processamento"))
                // // Endpoint do LocalStack
                .credentialsProvider(credentialsProvider)
                .build();
    }

    @Bean
    public SnsTemplate snsTemplate(SnsClient snsClient) {
        return new SnsTemplate(snsClient);
    }
}
