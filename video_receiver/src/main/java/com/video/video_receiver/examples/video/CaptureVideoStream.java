package com.video.video_receiver.examples.video;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeListener;
import java.io.ByteArrayInputStream;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;

import static com.video.video_receiver.examples.video.PlayVideo.Mat2BufferedImage;

public class CaptureVideoStream {

    static VideoCapture webSource;
    static Mat frame;
    static JPanel jPanel1;
    static  MatOfByte mem;
    static DaemonThread myThread = null;
    static JFrame jframe;
    static JLabel videoLabel;

    static {
        try {
            System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
            System.out.println("OpenCV Loaded");

        } catch (UnsatisfiedLinkError e) {
            System.err.println("Native code library failed to load.\n" + e);
            System.exit(1);
        }
        frame = new Mat();
        jPanel1 = new JPanel();
        mem = new MatOfByte();
        videoLabel = new JLabel();
    }

    //imports
    //-Djava.library.path="C:\opencv\build\java\x86"
    public static void main(String[] args) {

         jframe = new JFrame("Video Title");
//
//        //Inform jframe what to do in the event that you close the program
//        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        //Create a new JLabel object vidpanel
//        JLabel vidPanel = new JLabel();
//
//        //assign vidPanel to jframe
//        jframe.setContentPane(vidPanel);
//
//        //set frame size
//        jframe.setSize(820, 628);
//
//        //make jframe visible
//        jframe.setVisible(true);

        JButton startButton = new JButton("1");
        JButton stopButton = new JButton("2");
        // definitions

        int count = 0;


        //Mat frame = new Mat();

        ////////////////////////////////////////////////////////////
        /// start button
        webSource = new VideoCapture(0);

        myThread = new DaemonThread();

        Thread t = new Thread(myThread);
        t.setDaemon(true);
        myThread.runnable = true;
        t.start();
        //start button
        startButton.setText("Start");
        startButton.setEnabled(true);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myThread.runnable = true;
                synchronized (myThread) {
                    myThread.notifyAll();
                }
                startButton.setText("Started");
                stopButton.setEnabled(true);

            }
        });

        //stop button
        myThread.runnable = false;
        stopButton.setText("Stop");
        stopButton.setEnabled(false);
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myThread.runnable = false;
                startButton.setText("Start");
                stopButton.setEnabled(false);

            }
        });




        jPanel1.add(startButton);
        jPanel1.add(stopButton);
        jPanel1.add(videoLabel);
        jframe.setContentPane(jPanel1);
        jPanel1.setSize(600,400);
        jPanel1.setBorder(BorderFactory.createLineBorder(Color.black));
        jframe.setSize(820, 628);
        jframe.setVisible(true);

        //webSource.release();

    }
    /////////////////////////////////////////////////////////////////////
    static class DaemonThread implements Runnable {
        volatile boolean runnable = false;

        @Override
        public void run() {
            synchronized (this) {
                while (runnable) {
                    if (webSource.grab()) {
                        try {
                            webSource.retrieve(frame);


                            //1
                            ImageIcon image = new ImageIcon(Mat2BufferedImage(frame));
                            videoLabel.setIcon(image);
                            videoLabel.repaint();


                            //2
                            Imgcodecs.imencode(".bmp", frame, mem);
                            Image im = ImageIO.read(new ByteArrayInputStream(mem.toArray()));

                            BufferedImage buff = (BufferedImage) im;






                            //
                            Graphics g = jPanel1.getGraphics();
                            System.out.println("Running, buf width:"+buff.getWidth());
                            if (g.drawImage(buff, 0, 0, getWidth(), getHeight() - 150, 0, 0, 600,400, null))
                                jPanel1.repaint();
                                System.out.println("Drwaing");
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

