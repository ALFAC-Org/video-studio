package br.com.alfac.videostudio.unit.infra.config;

import br.com.alfac.videostudio.infra.config.SQSConfig;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;
import software.amazon.awssdk.services.sqs.SqsClient;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SQSConfigTest {

    @Test
    public void testSqsClientLocal() {
        SQSConfig sqsConfig = new SQSConfig();
        ReflectionTestUtils.setField(sqsConfig, "accessKey", "testAccessKey");
        ReflectionTestUtils.setField(sqsConfig, "secretKey", "testSecretKey");
        ReflectionTestUtils.setField(sqsConfig, "awsRegion", "us-east-1");
        ReflectionTestUtils.setField(sqsConfig, "sqsEndpoint", "http://localhost:4566");

        SqsClient sqsClient = sqsConfig.sqsClientLocal();
        assertNotNull(sqsClient);
        assertTrue(sqsClient.serviceName().equals("sqs"));
    }

    @Test
    public void testSqsClientProd() {
        SQSConfig sqsConfig = new SQSConfig();
        ReflectionTestUtils.setField(sqsConfig, "accessKey", "testAccessKey");
        ReflectionTestUtils.setField(sqsConfig, "secretKey", "testSecretKey");
        ReflectionTestUtils.setField(sqsConfig, "awsRegion", "us-east-1");
        ReflectionTestUtils.setField(sqsConfig, "sessionToken", "testSessionToken");

        SqsClient sqsClient = sqsConfig.sqsClientProd();
        assertNotNull(sqsClient);
        assertTrue(sqsClient.serviceName().equals("sqs"));
    }

    @Test
    public void testSqsClientTest() {
        SQSConfig sqsConfig = new SQSConfig();
        SqsClient sqsClient = sqsConfig.sqsClientTest();
        assertNotNull(sqsClient);
        assertTrue(Mockito.mockingDetails(sqsClient).isMock());
    }

    @Test
    public void testSqsAsyncClientLocal() {
        SQSConfig sqsConfig = new SQSConfig();
        ReflectionTestUtils.setField(sqsConfig, "accessKey", "testAccessKey");
        ReflectionTestUtils.setField(sqsConfig, "secretKey", "testSecretKey");
        ReflectionTestUtils.setField(sqsConfig, "awsRegion", "us-east-1");
        ReflectionTestUtils.setField(sqsConfig, "sqsEndpoint", "http://localhost:4566");

        SqsAsyncClient sqsAsyncClient = sqsConfig.sqsAsyncClientLocal();
        assertNotNull(sqsAsyncClient);
        assertTrue(sqsAsyncClient.serviceName().equals("sqs"));
    }

    @Test
    public void testSqsAsyncClientProd() {
        SQSConfig sqsConfig = new SQSConfig();
        ReflectionTestUtils.setField(sqsConfig, "accessKey", "testAccessKey");
        ReflectionTestUtils.setField(sqsConfig, "secretKey", "testSecretKey");
        ReflectionTestUtils.setField(sqsConfig, "awsRegion", "us-east-1");
        ReflectionTestUtils.setField(sqsConfig, "sessionToken", "testSessionToken");

        SqsAsyncClient sqsAsyncClient = sqsConfig.sqsAsyncClientProd();
        assertNotNull(sqsAsyncClient);
        assertTrue(sqsAsyncClient.serviceName().equals("sqs"));
    }

    @Test
    public void testSqsAsyncClientTest() {
        SQSConfig sqsConfig = new SQSConfig();
        SqsAsyncClient sqsAsyncClient = sqsConfig.sqsAsyncClientTest();
        assertNotNull(sqsAsyncClient);
        assertTrue(Mockito.mockingDetails(sqsAsyncClient).isMock());
    }
}
