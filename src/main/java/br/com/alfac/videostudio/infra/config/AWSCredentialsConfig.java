package br.com.alfac.videostudio.infra.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.AwsSessionCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;

@Configuration
public class AWSCredentialsConfig {
  @Value("${aws.accessKey}")
  private String accessKey;

  @Value("${aws.secretKey}")
  private String secretKey;

  @Value("${aws.sessionToken}")
  private String sessionToken;

  // private static StaticCredentialsProvider credentialsProviderInstance;

  // synchronized StaticCredentialsProvider getCredentialsProvider() {
  // if (credentialsProviderInstance == null) {
  // credentialsProviderInstance = StaticCredentialsProvider.create(
  // AwsSessionCredentials.create(accessKey, secretKey, sessionToken));
  // }
  // return credentialsProviderInstance;
  // }

  @Bean
  public AwsCredentialsProvider credentialsProvider() {
    return StaticCredentialsProvider.create(AwsSessionCredentials.create(accessKey, secretKey, sessionToken));
  }
}
