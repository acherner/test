package com.video.video_receiver.examples.video;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import javax.imageio.ImageIO;
import javax.swing.*;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;

public class CaptureVideoStream {

    static VideoCapture webSource = null;
    static Mat frame;
    static JPanel jPanel1 = new JPanel();
    static  MatOfByte mem = new MatOfByte();

    static {
        try {
            System.load( "C:\\Users\\anatolyc\\IdeaProjects\\mgenie code\\test\\opencsv342\\opencv\\build\\java\\x64\\opencv_java342.dll" );
        } catch (UnsatisfiedLinkError e) {
            System.err.println("Native code library failed to load.\n" + e);
            System.exit(1);
        }
        frame = new Mat();
    }

    //imports
    //-Djava.library.path="C:\opencv\build\java\x86"
    public static void main(String[] args) {

        JButton jButton1 = new JButton("1");
        JButton jButton2 = new JButton("2");
        // definitions
        DaemonThread myThread = null;
        int count = 0;


        Mat frame = new Mat();

        ////////////////////////////////////////////////////////////
        /// start button
        webSource = new VideoCapture(0);

        myThread = new DaemonThread();

        Thread t = new Thread(myThread);
        t.setDaemon(true);
        myThread.runnable = true;
        t.start();
        jButton1.setEnabled(false);  //start button
        jButton2.setEnabled(true);  // stop button
        //////////////////////////////////////////////////////
        /// stop button
        myThread.runnable = false;
        jButton2.setEnabled(false);
        jButton1.setEnabled(true);

        webSource.release();

    }
    /////////////////////////////////////////////////////////////////////
    static class DaemonThread implements Runnable {
        protected volatile boolean runnable = false;

        @Override
        public void run() {
            synchronized (this) {
                while (runnable) {
                    if (webSource.grab()) {
                        try {
                            webSource.retrieve(frame);
                            Imgcodecs.imencode(".bmp", frame, mem);
                            Image im = ImageIO.read(new ByteArrayInputStream(mem.toArray()));

                            BufferedImage buff = (BufferedImage) im;
                            Graphics g = jPanel1.getGraphics();

                            if (g.drawImage(buff, 0, 0, getWidth(), getHeight() - 150, 0, 0, buff.getWidth(), buff.getHeight(), null))

                                if (runnable == false) {
                                    System.out.println("Going to wait()");
                                    this.wait();
                                }
                        } catch (Exception ex) {
                            System.out.println("Error");
                        }
                    }
                }
            }
        }

        private int getHeight() {
            return 100;
        }

        private int getWidth() {
            return 100;
        }
    }
/////////////////////////////////////////////////////////

// add this line to main method

    //          System.loadLibrary(Core.NATIVE_LIBRARY_NAME); // load native library of opencv

}

