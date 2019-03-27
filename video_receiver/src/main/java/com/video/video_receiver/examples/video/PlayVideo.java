package com.video.video_receiver.examples.video;

import org.opencv.core.*;
import org.opencv.videoio.*;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

import javax.swing.*;


public class PlayVideo {

    static {
        try {
            System.load("/usr/local/Cellar/opencv/3.4.3_2/share/OpenCV/java/libopencv_java343.dylib");
            //System.load( "C:\\Users\\anatolyc\\IdeaProjects\\mgenie code\\test\\opencsv342\\opencv\\build\\java\\x64\\opencv_java342.dll" );
        } catch (UnsatisfiedLinkError e) {
            System.err.println("Native code library failed to load.\n" + e);
            System.exit(1);
        }
    }

    public static void main(String[] args)
    {
        //System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        System.load("/usr/local/Cellar/opencv/3.4.3_2/share/OpenCV/java/libopencv_java343.dylib");

        //Create new MAT object
        Mat frame = new Mat();

        //Create new VideoCapture object
        //VideoCapture camera = new VideoCapture("C:\\Users\\anatolyc\\IdeaProjects\\mgenie code\\test\\video_receiver\\src\\main\\resources\\Megamind.avi");
        VideoCapture camera = new VideoCapture("/Users/natan/IdeaProjects/test/video_receiver/src/main/resources/Megamind.avi");
        //Create new JFrame object
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
    }

    public static BufferedImage Mat2BufferedImage(Mat m) {
        //Method converts a Mat to a Buffered Image
        int type = BufferedImage.TYPE_BYTE_GRAY;
         if ( m.channels() > 1 ) {
             type = BufferedImage.TYPE_3BYTE_BGR;
         }
         int bufferSize = m.channels()*m.cols()*m.rows();
         byte [] b = new byte[bufferSize];
         m.get(0,0,b); // get all the pixels
         BufferedImage image = new BufferedImage(m.cols(),m.rows(), type);
         final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
         System.arraycopy(b, 0, targetPixels, 0, b.length);
         return image;
    }
}