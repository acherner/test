package com.video.video_receiver.examples.visualize;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;


/**
 * shows image, image loaded from resources
 */
//https://docs.opencv.org/master/d5/d98/tutorial_mat_operations.html
public class VisualizeImage {

    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        //System.load( "C:\\Users\\PC\\IdeaProjects\\test\\opencsv342\\opencv\\build\\java\\x64\\opencv_java342.dll" );

        //Utils.loadResource("a");
        File resource = null;
        try {
            resource = new ClassPathResource(
                    "image.jpg").getFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String path = resource.getAbsolutePath();
        Mat img = Imgcodecs.imread(path);//"C:\\Users\\anatolyc\\IdeaProjects\\mgenie code\\test\\video_receiver\\src\\main\\resources\\image.jpg");
        HighGui.namedWindow("image", HighGui.WINDOW_AUTOSIZE);
        HighGui.imshow("image", img);
        HighGui.waitKey();
    }
}
