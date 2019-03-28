package com.video.video_receiver.examples.video;


import java.util.List;

import org.opencv.core.*;
import org.opencv.highgui.HighGui;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;

class ObjectDetection {

    public void detectAndDisplay(Mat frame, CascadeClassifier faceCascade, CascadeClassifier eyesCascade) {
        Mat frameGray = new Mat();
        Imgproc.cvtColor(frame, frameGray, Imgproc.COLOR_BGR2GRAY);
        Imgproc.equalizeHist(frameGray, frameGray);
        // -- Detect faces
        MatOfRect faces = new MatOfRect();
        try{
            faceCascade.detectMultiScale(frameGray, faces);
        }
        catch(Exception e){
         System.out.println("error");
         e.printStackTrace();
        }

        List<Rect> listOfFaces = faces.toList();
        for (Rect face : listOfFaces) {
            Point center = new Point(face.x + face.width / 2, face.y + face.height / 2);

            Size size =  new Size( 5,5);//face.width / 2, face.height / 2);
            size.width = 2;
            Imgproc.ellipse(frame, center, size, 0, 0, 360,
                    new Scalar(255, 0, 255));
            Mat faceROI = frameGray.submat(face);
            // -- In each face, detect eyes
            MatOfRect eyes = new MatOfRect();
            try {
                eyesCascade.detectMultiScale(faceROI, eyes);
            }
            catch(Exception e) {
                System.out.println("error 2");
            }

            List<Rect> listOfEyes = eyes.toList();
            for (Rect eye : listOfEyes) {
                Point eyeCenter = new Point(face.x + eye.x + eye.width / 2, face.y + eye.y + eye.height / 2);
                int radius = (int) Math.round((eye.width + eye.height) * 0.25);
                Imgproc.circle(frame, eyeCenter, radius, new Scalar(255, 0, 0), 4);
            }
        }
        //-- Show what you got
        HighGui.imshow("Capture - Face detection", frame );
    }

    public void run(String[] args) {
        // Be sure the program will find the path of files haarcascade_frontalface_alt.xml and haarcascade_eye_tree_eyeglasses.xml.
        // They are located in opencv/data/haarcascades
        //System.load("/usr/local/Cellar/opencv/3.4.3_2/share/OpenCV/java/libopencv_java343.dylib");
        System.out.println("openCV name:"+ Core.NATIVE_LIBRARY_NAME);
        String filenameFaceCascade = args.length > 2 ? args[0] : "/usr/local/Cellar/opencv/3.4.3_2/share/OpenCV/haarcascades/haarcascade_frontalface_alt.xml";
        String filenameEyesCascade = args.length > 2 ? args[1] : "/usr/local/Cellar/opencv/3.4.3_2/share/OpenCV/haarcascades/haarcascade_eye_tree_eyeglasses.xml";
        int cameraDevice = args.length > 2 ? Integer.parseInt(args[2]) : 0;
        CascadeClassifier faceCascade = new CascadeClassifier();
        CascadeClassifier eyesCascade = new CascadeClassifier();
        if (!faceCascade.load(filenameFaceCascade)) {
            System.err.println("--(!)Error loading face cascade: " + filenameFaceCascade);
            System.exit(0);
        }
        if (!eyesCascade.load(filenameEyesCascade)) {
            System.err.println("--(!)Error loading eyes cascade: " + filenameEyesCascade);
            System.exit(0);
        }
        VideoCapture capture = new VideoCapture(cameraDevice);
        if (!capture.isOpened()) {
            System.err.println("--(!)Error opening video capture");
            System.exit(0);
        }
        Mat frame = new Mat();
        while (capture.read(frame)) {
            if (frame.empty()) {
                System.err.println("--(!) No captured frame -- Break!");
                break;
            }
            //-- 3. Apply the classifier to the frame
            detectAndDisplay(frame, faceCascade, eyesCascade);
            if (HighGui.waitKey(10) == 27) {
                break;// escape
            }
        }
        System.exit(0);
    }
}