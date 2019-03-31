package com.video.video_receiver.controller;

import com.adventurer.springserialport.connector.properties.SerialPortProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(
        prefix = "sport"
)
public class Props extends SerialPortProperties {

}
