package edu.westga.cs3151.animalGame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

/**
 * The Class Main.
 * 
 * @author wtjra
 * @version Spring 2022
 */
public class Main extends Application {
	public static final String WINDOW_TITLE = "Animal guessing Game: by William Jones";
	public static final String STARTPAGE = "view/AnimalGameGUI.fxml";
	public static final String QUESTIONPAGE = "view/QuestionGUI.fxml";
	public static final String YOUWONPAGE = "view/YouWonGUI.fxml";
	public static final String COMPUTERWONPAGE = "view/ComputerWonGUI.fxml";

	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource(Main.STARTPAGE));
			Pane pane = loader.load();
			Scene scene = new Scene(pane);
			primaryStage.setScene(scene);
			primaryStage.setTitle(Main.WINDOW_TITLE);
			primaryStage.show();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
