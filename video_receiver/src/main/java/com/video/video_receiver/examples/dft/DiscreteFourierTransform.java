package com.video.video_receiver.examples.dft;

import org.opencv.core.Core;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;

//https://docs.opencv.org/master/d8/d01/tutorial_discrete_fourier_transform.html
public class DiscreteFourierTransform {
    public static void main(String[] args) {
        // Load the native library.
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        String[] ar = new String[1];

        File resource = null;
        try {
            resource = new ClassPathResource(
                    "image.jpg").getFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String path = resource.getAbsolutePath();
        ar[0] = path;
        //ar[0] = "C:\\Users\\anatolyc\\IdeaProjects\\mgenie code\\test\\video_receiver\\src\\main\\resources\\image.jpg";
        new DiscreteFourierTransformRun().run(ar);
    }
}