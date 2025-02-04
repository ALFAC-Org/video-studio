package br.com.alfac.videostudio.unit.infra.config;

import br.com.alfac.videostudio.infra.config.S3Config;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;
import software.amazon.awssdk.services.s3.S3Client;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class S3ConfigTest {

    @Test
    public void testS3ClientLocal() {
        S3Config s3Config = new S3Config();
        ReflectionTestUtils.setField(s3Config, "accessKey", "testAccessKey");
        ReflectionTestUtils.setField(s3Config, "secretKey", "testSecretKey");
        ReflectionTestUtils.setField(s3Config, "awsRegion", "us-east-1");
        ReflectionTestUtils.setField(s3Config, "s3Endpoint", "http://localhost:4566");

        S3Client s3Client = s3Config.s3ClientLocal();
        assertNotNull(s3Client);
        assertTrue(s3Client.serviceName().equals("s3"));
    }

    @Test
    public void testS3ClientProd() {
        S3Config s3Config = new S3Config();
        ReflectionTestUtils.setField(s3Config, "accessKey", "testAccessKey");
        ReflectionTestUtils.setField(s3Config, "secretKey", "testSecretKey");
        ReflectionTestUtils.setField(s3Config, "awsRegion", "us-east-1");
        ReflectionTestUtils.setField(s3Config, "sessionToken", "testSessionToken");

        S3Client s3Client = s3Config.s3ClientProd();
        assertNotNull(s3Client);
        assertTrue(s3Client.serviceName().equals("s3"));
    }

    @Test
    public void testS3ClientTest() {
        S3Config s3Config = new S3Config();
        S3Client s3Client = s3Config.s3ClientTest();
        assertNotNull(s3Client);
        assertTrue(Mockito.mockingDetails(s3Client).isMock());
    }
}
