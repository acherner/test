package com.video.video_receiver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan("com.adventurer.springserialport.connector.properties")
public class VideoReceiverApplication {

    public static void main(String[] args) {
        SpringApplication.run(VideoReceiverApplication.class, args);
    }

}

