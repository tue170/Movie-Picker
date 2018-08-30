package application.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import application.model.Movie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;

/**
 * 
 * @author Team Mediocre
 * Jennifer Nguyen(zfv431) 
 * Tiffany Tsai (tue170) 
 * Keegan Knisely (Har336)
 *
 */
public class MovieListController implements Initializable {


	@FXML
	Button delete;
	@FXML
	Button submit;
	@FXML
	Button next;
	@FXML
	ComboBox<String> Selection;
	@FXML 
	Label deleteErr;
	@FXML
	Label guide;
	@FXML 
	Label nextErr;
	@FXML
	private ListView<Movie> MList;
	@FXML
	MenuBar bar;
	@FXML
	Text movieText;
	@FXML
	Text locationText;
	@FXML
	TextField location;
	@FXML
	TextField MovieNameTextField;
	@FXML
	TextField deleteMovie;
	
	
	private static String selectedFileName;
	private File file;
	private FileWriter writer;
	private StringBuilder string;
	private ObservableList<Movie> list;
	private String temp;
	private BufferedReader buffer;
	

	/**
	 * This method is to handle the event in which a specific button is click on.
	 */
	public void loadNextView() {

		this.next.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				int counter = 0;
				for (int i = 0; i < 9; i++)
					if (Main.library.getShelves().get(i).getMovies().isEmpty())
						counter++;

				if (counter != 9)
					showNextView("GenreSelector");
				else
					nextErr.setText("Error: Movie list is empty, add a movie to list");
			}
		});

		this.submit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {

					// Selection.getPromptText()
					//System.out.println("Hello" + Selection.getValue());

					if (Selection.getValue() == null)
						Selection.setStyle("-fx-background-color: red;");
					else
						Selection.setStyle("-fx-background-color: gray;");

					if (MovieNameTextField.getText().isEmpty())
						movieText.setFill(Color.RED);
					else
						movieText.setFill(Color.WHITE);

					if ((!MovieNameTextField.getText().isEmpty()) && (!(Selection.getValue() == null))) {
						//System.out.println(location.getText());
						
						if (MovieNameTextField.getText().contains(",") || location.getText().contains(","))
						{
							MovieNameTextField.setText(MovieNameTextField.getText().replaceAll(",", "."));
							location.setText(location.getText().replaceAll(",", "."));
						}

						handleDataInput(MovieNameTextField.getText(), location.getText(), Selection.getValue());
						showNextView("MovieList1");

					}
					

				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		});

		this.delete.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				if(MList.getSelectionModel().getSelectedItem() == null)
					deleteErr.setText("Error: No movie has been selected to delete");
				else
				{
				handleDeleteLineInFile(MList.getSelectionModel().getSelectedItem().getTitle(),
						MList.getSelectionModel().getSelectedItem().getLocation());
				showNextView("MovieList1");
				}
			}
		});

	}

	/**
	 * This method is to initialize the variables and set the scene of Movie.FXML
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// TODO: Placement of variable declaration can be move
		Main.shelves = new String[] { "action", "children", "comedy", "drama", "fantasy", "horror", "musical",
				"romance", "other" };

		Selection.getItems().addAll("Action", "Children", "Comedy", "Drama", "Fantasy", "Horror", "Musical", "Romance",
				"Other");
		deleteErr.setText("");
		nextErr.setText("");
		populateList();
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
	 * This method is to show/load the Library.FXML
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
	 * This method is used to handle user option to delete
	 * Re-write the file.
	 * @param movie , a string that represent the movie title
	 * @param location , a string that represent the location of the movie
	 * @return a true for successful overwrite file, false for unsucessful
	 */
	private boolean handleDeleteLineInFile(String movie, String location) {
		file = new File("./src/resources/" + getSelectedFileName());
		File tempFile = new File("temp.txt");

		
		BufferedReader reader = null;
		BufferedWriter writer = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			writer = new BufferedWriter(new FileWriter(tempFile));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String temp;

		//TODO: fix delete if same movie title but different genre or different location
		//System.out.println(movie + " " +location);
		try {
			while ((temp = reader.readLine()) != null) {
				//System.out.println("LINE FROM READ" +temp);
				String[] lineSplit = temp.split(",");
				if (!lineSplit[1].equals(movie)) {
					//System.out.println(" PASSED CONDITIION "+ temp);
					writer.write(temp + System.getProperty("line.separator"));
				}

			}
	
			writer.close();
			reader.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		file.delete(); // delete existing file to rename the file. 
						// renameTo didn't work did to open stream that were been used or b/c of windows.
		//file.exists();

		boolean successful = tempFile.renameTo(new File("./src/resources/" + getSelectedFileName()));
		tempFile.deleteOnExit();
		
		//System.out.println(successful);
		return true;
	}

	/**
	 * This method is used to handle user input.
	 * Re-write the file.
	 * @param movie , a string that represent the movie title
	 * @param location , a string that represent the location of the movie
	 * @param genre , a string that represent the genre selected when submitted
	 * @return a true for successful write to file, false for unsucessful
	 * @throws IOException, throws expection related to file.
	 */
	private boolean handleDataInput(String movie, String location, String genre) throws IOException {
		file = new File("./src/resources/" + getSelectedFileName());
		writer = new FileWriter(file, true);
		string = new StringBuilder();

		// String house, String name, String year
		string.append(genre);
		string.append(",");
		string.append(movie);
		string.append(",");
		string.append(location);
		string.append('\n');

		writer.write(string.toString());
		// System.out.println(string.toString());

		writer.flush();
		writer.close();

		string.delete(0, string.length());
		
		return true;
	}

	/**
	 * This method is used to populated the list view in MovieList.FXML
	 */
	private void populateList() {
		try {
			Main.library.parseSelectedFile(getSelectedFileName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		setList(FXCollections.observableArrayList());
		getList().clear();

		for (int i = 0; i < 9; i++) {
			getList().addAll(Main.library.getShelves().get(i).getMovies());
		}
		getMovieList().getItems().setAll(getList().sorted());
	}

	// The method was created to generalize the transtition between FXML/Scene
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

	public static String getSelectedFileName() {
		return selectedFileName;
	}

	public static void setSelectedFileName(String selectedFileName) {
		MovieListController.selectedFileName = selectedFileName;
	}

	public ListView<Movie> getMovieList() {
		return MList;
	}

	public void setList(ListView<Movie> MList) {
		this.MList = MList;
	}

	public ObservableList<Movie> getList() {
		return list;
	}

	public void setList(ObservableList<Movie> list) {
		this.list = list;
	}

}