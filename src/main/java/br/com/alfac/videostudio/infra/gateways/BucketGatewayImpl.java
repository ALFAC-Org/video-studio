package br.com.alfac.videostudio.infra.gateways;

import br.com.alfac.videostudio.core.application.adapters.gateways.BucketGateway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3Configuration;
import software.amazon.awssdk.services.s3.model.*;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;

import java.nio.file.Path;
import java.time.Duration;
import java.util.Optional;

@Component
public class BucketGatewayImpl implements BucketGateway {

    private final S3Client s3Client;
    private final S3Presigner s3Presigner;

    @Value("${s3.expiration}")
    private Duration expiration;

    public BucketGatewayImpl(final S3Client s3Client, final S3Presigner s3Presigner) {
        this.s3Client = s3Client;
        this.s3Presigner = s3Presigner;
    }


    @Override
    public Optional<String> generatePresignedUrl(String objectKey, String bucketName) {

        if (checkIfFileExists(s3Client, bucketName, objectKey)) {
            GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                    .bucket(bucketName)
                    .key(objectKey)
                    .build();

            GetObjectPresignRequest getObjectPresignRequest = GetObjectPresignRequest.builder()
                    .signatureDuration(expiration)
                    .getObjectRequest(getObjectRequest)
                    .build();

            return Optional.of(s3Presigner.presignGetObject(getObjectPresignRequest).url().toString());
        }

        return Optional.empty();

    }

    public void uploadFile(String key, Path filePath, String bucketName) {
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();

        s3Client.putObject(putObjectRequest, software.amazon.awssdk.core.sync.RequestBody.fromFile(filePath));
    }

    private boolean checkIfFileExists(S3Client s3Client, String bucketName, String fileName) {
        try {
            HeadObjectRequest request = HeadObjectRequest.builder()
                    .bucket(bucketName)
                    .key(fileName)
                    .build();

            // Tenta obter os metadados do objeto
            HeadObjectResponse response = s3Client.headObject(request);
            return response != null;
        } catch (S3Exception e) {
            // Retorna falso se o arquivo não existir
            if (e.statusCode() == 404) {
                return false;
            }
            throw e; // Relança a exceção para outros erros
        }
    }

}
