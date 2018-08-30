package application.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.Main;
import application.model.Movie;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 * 
 * @author Team Mediocre 
 * Jennifer Nguyen(zfv431) 
 * Tiffany Tsai (tue170) 
 * Keegan Knisely (Har336)
 *
 */
public class GenreController implements Initializable, EventHandler<Event> {

	public static String selectedGenre;

	@FXML
	Button ButtonG1;
	@FXML
	Button ButtonG2;
	@FXML
	Button ButtonG3;
	@FXML
	Button ButtonG4;
	@FXML
	Button ButtonG5;
	@FXML
	Button ButtonG6;
	@FXML
	Button ButtonG7;
	@FXML
	Button ButtonG8;
	@FXML
	Button ButtonG9;
	@FXML
	Button ButtonF;
	@FXML
	GridPane gridPane;
	@FXML
	Label genreLabel;
	ArrayList<Button> button;
	FXMLLoader loader;

	@Override
	/**
	 * This event handle is for all the button beside the "Chose regardless genre"
	 * button
	 */
	public void handle(Event event) {
		GenreController.selectedGenre = String.valueOf(event.getTarget());
		Movie temp = Main.library.grabMovie(String.valueOf(event.getTarget()));
		MovieController.setRandomMovie(temp);
		showNextView("MoviePop");
	}

	/**
	 * This event handle is for "Chose regardless of genre" button
	 */
	public void all(Event event) {
		GenreController.selectedGenre = "random";
		Movie temp = Main.library.grabMovie();
		MovieController.setRandomMovie(temp);
		showNextView("MoviePop");
	}

	/**
	 * This method show the main view in the event that the home button was clicked
	 * on
	 */
	public void showMain() {
		Main.stage.setScene(Main.scene);
		Main.stage.show();
	}

	/**
	 * This method show the Library view in the event that the library button was
	 * clicked on
	 */
	public void showLibrary() {
		AnchorPane root = null;
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(Main.class.getResource("../application/view/Library.fxml"));
		try {
			root = (AnchorPane) fxmlLoader.load();

		} catch (IOException e1) {
			e1.printStackTrace();
		}
		Scene scene = new Scene(root);
		Main.stage.setScene(scene);
		Main.stage.show();
	}

	/**
	 * This method show the Movie List view in the event that the movie List button
	 * was clicked on
	 */
	public void showMovielist() {
		AnchorPane root = null;
		FXMLLoader fxmlLoader = new FXMLLoader();
		MovieListController.setSelectedFileName(LibraryController.getSelectFile());
		fxmlLoader.setLocation(Main.class.getResource("../application/view/MovieList1.fxml"));
		try {
			root = (AnchorPane) fxmlLoader.load();

		} catch (IOException e1) {
			e1.printStackTrace();
		}
		Scene scene = new Scene(root);
		Main.stage.setScene(scene);
		Main.stage.show();
	}

	/**
	 * This method is to initialize the variables and set the scene of
	 * GenreSelector.FXML
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		button = new ArrayList<Button>();

		button.add(ButtonG1);
		button.add(ButtonG2);
		button.add(ButtonG3);
		button.add(ButtonG4);
		button.add(ButtonG5);
		button.add(ButtonG6);
		button.add(ButtonG7);
		button.add(ButtonG8);
		button.add(ButtonG9);

		for (int i = 0; i < 9; i++) {
			if (Main.library.getShelves().get(i).getMovies().isEmpty()) {
				button.get(i).setDisable(true);
			}

		}

	}

	/**
	 * This method is a generalize method used to show the next view.
	 * 
	 * @param name
	 *            , passed in string used to determine which FXML document to use.
	 */
	public void showNextView(String name) {
		try {
			loader = new FXMLLoader();

			loader.setLocation(Main.class.getResource("../application/view/" + name + ".fxml"));
			AnchorPane layout = (AnchorPane) loader.load();

			Scene scene = new Scene(layout);
			Main.stage.setScene(scene);
			Main.stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
