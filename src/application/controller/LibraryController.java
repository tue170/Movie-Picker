package application.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import application.Main;
import application.model.Library;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * 
 * @author Team Mediocre 
 * Jennifer Nguyen(zfv431) 
 * Tiffany Tsai (tue170) 
 * Keegan Knisely (Har336)
 *
 */
public class LibraryController implements Initializable {

	@FXML
	Button add;
	@FXML
	Button delete;
	@FXML
	Button submit;
	@FXML
	Label deleteErr;
	@FXML
	Label Label;
	@FXML
	Label selectErr;
	@FXML
	ListView<String> LibraryList;
	@FXML
	TextField file;

	private Path path;
	private static String selectFile;
	
	/**
	 * This method is to handle the event in which a specfic button is click on.
	 */
	public void loadNextView() {

		// Submit, upon a selection of a list the user may
		// transistion to the next view, where the movie can be edit/customize
		this.submit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// LibraryList.getSelectionModel().getSelectedItems().remove();
				if (LibraryList.getSelectionModel().getSelectedIndex() >= 0) {
					MovieListController.setSelectedFileName(LibraryList.getSelectionModel().getSelectedItem());
					showNextView("MovieList1");
				} else
					selectErr.setText("Error: User must select a file");
			}
		});

		// add, adds a new file to the list.
		// Note: that the showNextView passed in the Library FXMl.
		// similar to an update or refresh screen.
		this.add.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				String filename = file.getText();
				if (!filename.isEmpty()) {
					File file = new File("./src/resources/" + filename + ".csv");
					try {
						file.createNewFile();
						// if (file.createNewFile())
						// System.out.println("New File");
					} catch (IOException e) {
						e.printStackTrace();
					}
					showNextView("library");
				}
			}
		});

		/*
		 * Note: Do not touch this please. This is for the select property for listView
		 */
		this.LibraryList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

				setPath(Paths.get(System.getProperty("user.dir") + "/src/resources/" + newValue));
				// System.out.println("You Selected " + newValue);
				setSelectFile(newValue);

			}
		});

		/*
		 * This is a working event handler, please do no touch. This method deleted the
		 * selected file from the list View
		 */
		this.delete.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (getPath() == (null))
					deleteErr.setText("Error: No File has been Selected to Delete");

				else {
					if (getPath().endsWith("movie.csv"))
						deleteErr.setText("Error: Default File can't be Deleted");
					else {
						try {
							
							//System.out.println(getPath());
							Files.deleteIfExists(getPath());
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						showNextView("library");
					}
				}

			}
		});

	}

	/**
	 * This method is to initialize the variables and set the scene of Library.FXML
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		MainController.database = false;
		Main.library = new Library();
		LibraryList.setItems(Main.library.getFiles());
		deleteErr.setText("");
		selectErr.setText("");
		loadNextView();
	}

	/**
	 * This method is to show the main.FXML
	 */
	public void showMain() {
		Main.stage.setScene(Main.scene);
		Main.stage.show();
	}

	/**
	 * This method is a generalize method used to show the next view.
	 * 
	 * @param name
	 *            , passed in string used to determine which FXML document to use.
	 */
	public void showNextView(String name) {
		try {
			FXMLLoader loader = new FXMLLoader();

			loader.setLocation(Main.class.getResource("../application/view/" + name + ".fxml"));
			AnchorPane layout = (AnchorPane) loader.load();
			Scene scene = new Scene(layout);
			Main.stage.setScene(scene);
			Main.stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method is used to retrive the Library List
	 * 
	 * @return a ListView of Strings
	 */
	public ListView<String> getLibraryList() {
		return LibraryList;
	}

	/**
	 * This method is used to set the Library List view with an observableList
	 * 
	 * @param observableList
	 *            , an list of files for the Library List View
	 */
	public void setLibraryList(ObservableList<String> observableList) {
		LibraryList.setItems(observableList);
	}

	/**
	 * This method is used to retrive a path
	 * 
	 * @return a path to the working directory to the files
	 */
	public Path getPath() {
		return path;
	}

	/**
	 * This method is used to set a given path to the class variable
	 * 
	 * @param path
	 *            , a path from the wokring directory to the list of files.
	 */
	public void setPath(Path path) {
		this.path = path;
	}

	/**
	 * This method is a static method to retrieve the selected file to passed on to
	 * the Movie view and set the List View.
	 * 
	 * @return a string that represent the selected file
	 */
	public static String getSelectFile() {
		return selectFile;
	}

	/**
	 * This method is a static method to set the Selected file. Throughout the java
	 * class.
	 * 
	 * @param selectFile
	 *            a string that represe the selected file
	 */
	public static void setSelectFile(String selectFile) {
		LibraryController.selectFile = selectFile;
	}

}
