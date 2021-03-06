package com.video.video_receiver.examples.visualize;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;

/**
 * image converted to scale gray
 */
//https://docs.opencv.org/master/d5/d98/tutorial_mat_operations.html
public class VisualizeConstantly {
    public static void main(String[] args) {

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        File resource = null;
        try {
            resource = new ClassPathResource(
                    "image.jpg").getFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String path = resource.getAbsolutePath();
        Mat img = Imgcodecs.imread(path);

        Mat grey = Imgcodecs.imread(path);
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
