package application;


import java.sql.Connection;
import java.sql.DriverManager;

import application.model.Library;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;


/**
 * 
 * @author Team Exceptionally Mediocre 
 * Jennifer Nguyen(zfv431) 
 * Tiffany Tsai (tue170) 
 * Keegan Knisely (Har336)
 *
 */
public class Main extends Application {

	
	
	public static Stage stage;
	public static Scene scene;
	public static String[] shelves;
	
	public static Library library;
	private Connection connect;

	@Override
	public void start(Stage primaryStage) {
		connect = null;
		String url = "jdbc:sqlite:imdb-movies.db";
		Main.stage = primaryStage;
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../application/view/Main.fxml"));
			Main.scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Main.stage.getIcons().add(new Image(String.valueOf(getClass().getResource("../application/icon.png"))));
			Main.stage.setTitle("Welcome! Personal Movie Picker");
			connect = DriverManager.getConnection(url);
			primaryStage.setScene(Main.scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		launch(args);
	}
	
}
