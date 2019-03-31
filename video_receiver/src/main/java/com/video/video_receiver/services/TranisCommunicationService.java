package com.video.video_receiver.services;

import com.video.video_receiver.controller.SerialPortConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Service
public class TranisCommunicationService {

    @Autowired
    SerialPortConnector spc;

    @PostConstruct
    public void invokeResource() {
        try {
            spc.sendMessage("ATI1");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
