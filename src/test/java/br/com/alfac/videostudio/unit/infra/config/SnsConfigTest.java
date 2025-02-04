package br.com.alfac.videostudio.unit.infra.config;

import br.com.alfac.videostudio.infra.config.SnsConfig;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;
import software.amazon.awssdk.services.sns.SnsClient;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SnsConfigTest {

    @Test
    public void testSnsClientLocal() {
        SnsConfig snsConfig = new SnsConfig();
        ReflectionTestUtils.setField(snsConfig, "accessKey", "testAccessKey");
        ReflectionTestUtils.setField(snsConfig, "secretKey", "testSecretKey");
        ReflectionTestUtils.setField(snsConfig, "awsRegion", "us-east-1");
        ReflectionTestUtils.setField(snsConfig, "snsEndpoint", "http://localhost:4566");

        SnsClient snsClient = snsConfig.snsClientLocal();
        assertNotNull(snsClient);
        assertTrue(snsClient.serviceName().equals("sns"));
    }

    @Test
    public void testSnsClientProd() {
        SnsConfig snsConfig = new SnsConfig();
        ReflectionTestUtils.setField(snsConfig, "accessKey", "testAccessKey");
        ReflectionTestUtils.setField(snsConfig, "secretKey", "testSecretKey");
        ReflectionTestUtils.setField(snsConfig, "awsRegion", "us-east-1");
        ReflectionTestUtils.setField(snsConfig, "sessionToken", "testSessionToken");

        SnsClient snsClient = snsConfig.snsClientProd();
        assertNotNull(snsClient);
        assertTrue(snsClient.serviceName().equals("sns"));
    }

    @Test
    public void testSnsClientTest() {
        SnsConfig snsConfig = new SnsConfig();
        SnsClient snsClient = snsConfig.snsClientTest();
        assertNotNull(snsClient);
        assertTrue(Mockito.mockingDetails(snsClient).isMock());
    }
}
