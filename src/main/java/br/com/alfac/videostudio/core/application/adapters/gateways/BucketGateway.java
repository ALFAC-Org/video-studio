package br.com.alfac.videostudio.core.application.adapters.gateways;

public interface BucketGateway {

    String generatePresignedUrl(String objectKey);
}
