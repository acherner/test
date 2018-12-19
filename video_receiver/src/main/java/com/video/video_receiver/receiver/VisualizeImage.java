package com.video.video_receiver.receiver;

import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;

public class VisualizeImage {

    public static void main(String[] args) {
        System.load( "C:\\Users\\anatolyc\\IdeaProjects\\mgenie code\\test\\opencsv342\\opencv\\build\\java\\x64\\opencv_java342.dll" );

        Mat img = Imgcodecs.imread("C:\\Users\\anatolyc\\IdeaProjects\\mgenie code\\test\\video_receiver\\src\\main\\resources\\image.jpg");
        HighGui.namedWindow("image", HighGui.WINDOW_AUTOSIZE);
        HighGui.imshow("image", img);
        HighGui.waitKey();
    }
}
