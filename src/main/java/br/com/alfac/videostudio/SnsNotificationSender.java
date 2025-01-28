//package br.com.alfac.videostudio;
//
//import com.amazonaws.auth.AWSStaticCredentialsProvider;
//import com.amazonaws.auth.BasicAWSCredentials;
//import com.amazonaws.regions.Regions;
//import com.amazonaws.services.sns.AmazonSNS;
//import com.amazonaws.services.sns.AmazonSNSClientBuilder;
//import com.amazonaws.services.sns.model.MessageAttributeValue;
//import com.amazonaws.services.sns.model.PublishRequest;
//import com.amazonaws.services.sns.model.PublishResult;
//import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
//import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
//import software.amazon.awssdk.regions.Region;
//import software.amazon.awssdk.services.sns.SnsClient;
//import software.amazon.awssdk.services.sns.model.CreateTopicRequest;
//import software.amazon.awssdk.services.sns.model.CreateTopicResponse;
//import software.amazon.awssdk.services.sns.model.SnsException;
//
//import java.net.URI;
//import java.util.HashMap;
//import java.util.Map;
//
//public class SnsNotificationSender {
//
//    private static final String TOPIC_ARN = "arn:aws:sns:us-east-1:000000000000:queue-notification-error-processing"; // Substitua pelo ARN do seu tópico SNS
//
//    public static void main(String[] args) {
//
//
//        String email = "usuario@example.com";
//        String videoName = "meu_video.mp4";
//
//        BasicAWSCredentials awsCreds = new BasicAWSCredentials("test", "test");
//        AmazonSNS snsClient = AmazonSNSClientBuilder.standard()
//                .withRegion(Regions.US_EAST_1) // Substitua pela sua região
//                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
//                .build();
//        SnsClient snsClient2 = SnsClient.builder()
//                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create("test", "test")))
//                .endpointOverride(URI.create("http://localhost:4566")) // LocalStack Endpoint
//                .region(Region.US_EAST_1)
//                .build();
//        sendNotification(snsClient, email, videoName);
////        createSNSTopic(snsClient2, email, videoName);
//    }
//
//    public static String createSNSTopic(SnsClient snsClient, String topicName, String email ) {
//
//        CreateTopicResponse result = null;
//        try {
//            CreateTopicRequest request = CreateTopicRequest.builder()
//                    .name("queue-notification-error-processing")
//                    .attributes(new HashMap<String, String>() {{
//                        put("email", email);
//                        put("video_name", topicName);
//                    }})
//                    .build();
//
//            result = snsClient.createTopic(request);
//            return result.topicArn();
//        } catch (SnsException e) {
//
//            System.err.println(e.awsErrorDetails().errorMessage());
//            System.exit(1);
//        }
//        return "";
//    }
//
//    public static void sendNotification(AmazonSNS snsClient, String email, String videoName) {
//        Map<String, MessageAttributeValue> messageAttributes = new HashMap<>();
//        messageAttributes.put("email", new MessageAttributeValue()
//                .withDataType("String")
//                .withStringValue(email));
//        messageAttributes.put("video_name", new MessageAttributeValue()
//                .withDataType("String")
//                .withStringValue(videoName));
//
//        PublishRequest publishRequest = new PublishRequest()
//                .withTopicArn(TOPIC_ARN)
//                .withMessage("Erro no processamento do vídeo: " + videoName)
//                .withSubject("Erro no processamento do vídeo")
//                .withMessageAttributes(messageAttributes);
//
//        try {
//            PublishResult publishResult = snsClient.publish(publishRequest);
//            System.out.println("Mensagem enviada com sucesso: " + publishResult.getMessageId());
//        } catch (Exception e) {
//            System.err.println("Erro ao enviar mensagem: " + e.getMessage());
//        }
//    }
//}