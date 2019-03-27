package com.video.video_receiver.examples.video;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.videoio.VideoCapture;

import javax.swing.*;

import static com.video.video_receiver.examples.video.PlayVideo.Mat2BufferedImage;

/**
 * opens the webcam and captures one frame from video feed
 */
public class Webcam {

    public static void main (String args[]){

        System.out.println("Hello, OpenCV");
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        VideoCapture camera = new VideoCapture(0);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        camera.open(0); //Useless
        if(!camera.isOpened()){
            System.out.println("Camera Error");
        }
        else{
            System.out.println("Camera OK?");
        }

        Mat frame = new Mat();

        camera.grab();
        System.out.println("Frame Grabbed");
        camera.retrieve(frame);
        System.out.println("Frame Decoded");

        camera.read(frame);
        System.out.println("Frame Obtained");

    /* No difference
    camera.release();
    */

        System.out.println("Captured Frame Width " + frame.width());

        HighGui.imshow("camera.jpg", frame);
        HighGui.waitKey();

        JFrame jframe = new JFrame("Video Title");

        //Inform jframe what to do in the event that you close the program
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create a new JLabel object vidpanel
        JLabel vidPanel = new JLabel();

        //assign vidPanel to jframe
        jframe.setContentPane(vidPanel);

        //set frame size
        jframe.setSize(820, 628);

        //make jframe visible
        jframe.setVisible(true);

        while (true) {
            //If next video frame is available
            if (camera.read(frame)) {
                //Create new image icon object and convert Mat to Buffered Image
                ImageIcon image = new ImageIcon(Mat2BufferedImage(frame));
                //Update the image in the vidPanel
                vidPanel.setIcon(image);
                //Update the vidPanel in the JFrame
                vidPanel.repaint();

            }
        }
        //System.out.println("OK");
    }
}

