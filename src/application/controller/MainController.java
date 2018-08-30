package application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 * 
 * @author Team Mediocre 
 * Jennifer Nguyen(zfv431)
 * Tiffany Tsai (tue170) 
 * Keegan Knisely (Har336)
 *
 */
public class MainController implements Initializable {

	@FXML
	Button dataBaseButton;
	@FXML
	Button csvFileButton;
	FXMLLoader loader;
	public static boolean database;

	/**
	 * This method is to handle the event in which a specfic button is click on.
	 */
	public void loadNextView() {
		// This action handle is to handle when user click
		// on the Customize Option on the Main.FXML
		this.csvFileButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				database = false;
				// System.out.println(database);
				showNextView("library");

			}
		});
		// This action handle is to handle when user click
		// on the Now Option on the Main.FXML, Thinking about making
		// a database to handle the current now showing movies or
		// this year movie to make it more general
		// -- TODO: need to check if IMDB have a selection of the current year
		// To get an better feels of database.
		// OR MAYBE A MONTH....

		this.dataBaseButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				database = true;
				showNextView("DatabaseLib");

			}
		});
	}

	/**
	 * This method is to initialize the variables and set the scene of Main.FXML
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.csvFileButton.setText("Customize Movie List");
		this.dataBaseButton.setText("Generate From Database");
		loadNextView();
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
