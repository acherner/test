package com.video.video_receiver.controller;

import com.adventurer.springserialport.connector.AbstractSpringSerialPortConnector;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Component;

//@Log4j
@Component
public class SerialPortConnector extends AbstractSpringSerialPortConnector {

    @Override
    public void processData(String s) {
        System.out.println("s = [" + s + "]");
    }
}
