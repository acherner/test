package com.video.video_receiver.examples.visualize;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

//https://docs.opencv.org/master/d5/d98/tutorial_mat_operations.html
public class VisualizeConstantly {
    public static void main(String[] args) {
        System.load( "C:\\Users\\anatolyc\\IdeaProjects\\mgenie code\\test\\opencsv342\\opencv\\build\\java\\x64\\opencv_java342.dll" );

        Mat img = Imgcodecs.imread("C:\\Users\\anatolyc\\IdeaProjects\\mgenie code\\test\\video_receiver\\src\\main\\resources\\image.jpg");
        Mat grey = new Mat();
        Imgproc.cvtColor(img, grey, Imgproc.COLOR_BGR2GRAY);//Conversion from color to greyscale:
        Mat sobelx = new Mat();
        Imgproc.Sobel(grey, sobelx, CvType.CV_32F, 1, 0);
        Core.MinMaxLocResult res = Core.minMaxLoc(sobelx); // find minimum and maximum intensities
        Mat draw = new Mat();
        double maxVal = res.maxVal, minVal = res.minVal;
        sobelx.convertTo(draw, CvType.CV_8U, 255.0 / (maxVal - minVal), -minVal * 255.0 / (maxVal - minVal));
        HighGui.namedWindow("image", HighGui.WINDOW_AUTOSIZE);
        HighGui.imshow("image", draw);
        HighGui.waitKey();
    }
}
