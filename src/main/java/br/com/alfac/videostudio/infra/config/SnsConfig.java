package br.com.alfac.videostudio.infra.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;
@Configuration
public class SnsConfig {

    @Bean
    public SnsClient snsClient() {
        return SnsClient.builder()
                .region(software.amazon.awssdk.regions.Region.US_EAST_1)
                //.endpointOverride(URI.create("arn:aws:sns:us-east-1:000687245264:envia_email_erro_processamento")) // Endpoint do LocalStack
                .credentialsProvider(
                        StaticCredentialsProvider.create(AwsBasicCredentials.create("ASIAQAKHWQ7ICM7CVDQY", "IQoJb3JpZ2luX2VjED0aCXVzLXdlc3QtMiJGMEQCIBsBuge+kP9Y6bd997ROA+qYkC1zLEh9G4njHVdF9j5wAiBXEf1X/Ps/TAz/Slj/ercRB37xw8inE8RKcSTv79SaIyqsAghGEAAaDDAwMDY4NzI0NTI2NCIMqM3hqG1DrJ8SZw4TKokCYongwc2ICIoYpsHpF3gOSvB9c2YvRD60G40xY9RzO2iuK1LrHPmevT4YHhbutjEEOCkFWvOli9UCrrUw5gRLDqo19hGRVlM/mry/a86fYR09Tj+zs/ulynNgMck23XemBdVa49iWbwZ7fQUmBn6vpfvwEAOgwgKObRWETVZbte1Qm4sPw6SEcq7IC6IqpoQKKaYuchgiPDox3jadZODG5p7CXXbgqBcECr3F5YhZC7eQw92mYlREQZnbY/dxpCYT4vS+JyvEgDjU8ZjlUHElATo2ucjes6hO9fRTHXjOvzkSmRDbUBZawrFqi4cjFPWEd1pFxlJgfHwsiVYeechShkh8HsZ6NQhuBDDV6ti8BjqfAWrUNpy8FW15kpNIilb4lBrNSO/SdksdX+LAQv+qaRBGcJHA69uuIuldr6dKM+r7HRa+eR6tq8nmsADDsrC/xKKO4QvLA97oHJWrlXMEM/9XPdSpZF3udjRNP9UUSW7Bfi8PLb7jdewNOvC/p8Ln4xfG4iY62/NSOUUlQ0sccGOOgTgnkPyxctqh+Yom8MQgARA65946TgrXuPllpOUutw==")))
                .build();
    }

//    @Bean
//    public SnsTemplate snsTemplate(SnsClient snsClient) {
//        return new SnsTemplate(snsClient);
//    }
}
