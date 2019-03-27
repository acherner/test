package com.video.video_receiver.examples.visualize;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

/**
 * just checks that OpenCV can be found on local machine
 */
public class HelloCV {
    public static void main(String[] args){
        System.out.println("openCV name:"+Core.NATIVE_LIBRARY_NAME);
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        //System.load("/usr/local/Cellar/opencv/3.4.3_2/share/OpenCV/java/libopencv_java343.dylib");

        Mat mat = Mat.eye(3, 3, CvType.CV_8UC1);
        System.out.println("mat = " + mat.dump());
    }
}
