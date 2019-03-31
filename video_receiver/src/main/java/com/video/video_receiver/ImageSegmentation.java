package com.video.video_receiver;


import com.video.video_receiver.examples.imagesegmentation.ImageSegController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.opencv.core.Core;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.util.ResourceUtils;

import javax.annotation.Resource;
import java.io.File;

@SpringBootApplication
public class ImageSegmentation extends Application
{
	/**
	 * The main class for a JavaFX application. It creates and handle the main
	 * window with its resources (style, graphics, etc.).
	 * 
	 * This application apply the Canny filter to the camera video stream or try
	 * to remove a uniform background with the erosion and dilation operators.
	 * 
	 * @author <a href="mailto:luigi.derussis@polito.it">Luigi De Russis</a>
	 * @author <a href="mailto:alberto.cannavo@polito.it">Alberto Cannav�</a>
	 * @version 2.0 (2017-03-10)
	 * @since 1.0 (2013-12-20)
	 * 
	 */



	private ConfigurableApplicationContext springContext;
	private Parent rootNode;
	private FXMLLoader loader;

//	@Value("classpath:application.properties")//javafx.fxml/ImageSeg.javafx.fxml")
//	private org.springframework.core.io.Resource resource;

	@Override
	public void init() throws Exception {
		springContext = SpringApplication.run(ImageSegmentation.class);
		//File file = ResourceUtils.getFile("classpath:javafx.fxml/ImageSeg.xml");
		org.springframework.core.io.Resource r = springContext.getResource("classpath:javafx/fxml/ImageSeg.fxml");
//		java.net.URL location = ImageSegmentation.class.//getClass().
//				 getResource("classpath:javafx.fxml/ImageSeg.javafx.fxml");
		loader = new FXMLLoader(r.getURL());
		loader.setControllerFactory(springContext::getBean);
	}

	@Override
	public void start(Stage primaryStage)
	{
		try
		{
			// load the FXML resource
//			loader = new FXMLLoader(getClass().getResource("B:\\MY PROGRAMMING PROJECTS\\cvision\\test\\video_receiver\\src\\main\\resources\\javafx.fxml\\ImageSeg.javafx.fxml"));

//			FXMLLoader loader = new FXMLLoader(getClass().
//					getResource("/javafx.fxml/" + report.getClass().getCanonicalName().
//							substring(18).replaceAll("Controller", "") + ".javafx.fxml"));


			BorderPane root = (BorderPane) loader.load();
									
			// set a whitesmoke background
			root.setStyle("-fx-background-color: whitesmoke;");
			// create and style a scene
			Scene scene = new Scene(root, 800, 600);
			scene.getStylesheets().add(springContext.getResource("javafx/css/application.css").getURL().toExternalForm());
			// create the stage with the given title and the previously created
			// scene
			primaryStage.setTitle("Image Segmentation");
			primaryStage.setScene(scene);
			
			// show the GUI
			primaryStage.show();
			
			// get the controller
			ImageSegController controller = loader.getController();
			controller.init();
			
			// set the proper behavior on closing the application
			primaryStage.setOnCloseRequest((new EventHandler<WindowEvent>() {
				public void handle(WindowEvent we)
				{
					controller.setClosed();
				}
			}));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		// load the native OpenCV library
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		launch(args);
	}
}
