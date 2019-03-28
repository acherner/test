package com.video.video_receiver.examples.video;


import org.opencv.core.Core;




public class ObjectDetectionDemo {
    public static void main(String[] args) {
        // Load the native OpenCV library
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        //System.load("/usr/local/Cellar/opencv/3.4.3_2/share/OpenCV/java/libopencv_java343.dylib");
        new ObjectDetection().run(args);
    }
}