package br.com.alfac.videostudio.core.application.adapters.gateways;

import java.nio.file.Path;
import java.util.Optional;

public interface BucketGateway {

    Optional<String> generatePresignedUrl(String objectKey, String bucketName);

    void uploadFile(String key, Path filePath, String bucketName);
}
