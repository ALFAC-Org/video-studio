package br.com.alfac.videostudio;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "br.com.alfac.videostudio")
public class VideoStudioApplication {

    public static void main(String[] args) {
        SpringApplication.run(VideoStudioApplication.class, args);
    }

}