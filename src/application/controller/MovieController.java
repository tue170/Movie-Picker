package application.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.ResourceBundle;

import application.Main;
import application.model.Movie;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * 
 * @author Team Mediocre
 * Jennifer Nguyen(zfv431) 
 * Tiffany Tsai (tue170) 
 * Keegan Knisely (Har336)
 *
 */
public class MovieController implements Initializable {

	@FXML
	Button generate;
	@FXML
	Button regenerateFromBase;
	@FXML
	Label movieName;
	@FXML
	Label locationName;
	@FXML
	Label movieGenre;
	@FXML
	ImageView movieListIcon;
	@FXML
	ImageView libraryIcon;
	@FXML
	ImageView imdbIcon;

	private static Movie randomMovie;
	private String genre;
	private Float max, min;
	private int count;

	/**
	 * This method is to handle the event in which a specfic button is click on.
	 */
	public void loadNextView() {

		this.generate.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				retryMovie();
			}
		});

	}

	/**
	 * This method is to initialize the variables and set the scene of Movie.FXML
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if (MainController.database == true) {
			this.generate.setVisible(false);
			this.regenerateFromBase.setVisible(true);
			this.libraryIcon.setVisible(false);
			this.movieListIcon.setVisible(false);

		}
		if (MainController.database == false) {
			this.movieGenre.setVisible(false);
			movieName.setText(getRandomMovie().getTitle());
			locationName.setText(getRandomMovie().getLocation());
			this.generate.setVisible(true);
			this.regenerateFromBase.setVisible(false);
			this.libraryIcon.setVisible(true);
			this.movieListIcon.setVisible(true);
		}

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

	public void showMovielist() {
		AnchorPane root = null;
		FXMLLoader fxmlLoader = new FXMLLoader();
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
	 * This method is to show/load the Data base library
	 */
	public void showImdb() {

		AnchorPane root = null;
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(Main.class.getResource("../application/view/DatabaseLib.fxml"));
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
	 * This method regenerate a random movie from the CVS route
	 */
	public void retryMovie() {
		if (!GenreController.selectedGenre.equals("random")) {
			MovieController.randomMovie = Main.library.grabMovie(GenreController.selectedGenre);
			movieName.setText(randomMovie.getTitle());
			locationName.setText(randomMovie.getLocation());

		} else {
			MovieController.randomMovie = Main.library.grabMovie();
			movieName.setText(randomMovie.getTitle());
			locationName.setText(randomMovie.getLocation());

		}
	}

	/**
	 * This method is to retrive a random movie from the cvs route
	 * 
	 * @return a movie object that contain the movie' name and movie' location
	 */
	public static Movie getRandomMovie() {
		return randomMovie;
	}

	/**
	 * This method is to set the random movie class variable
	 * 
	 * @param randomMovie,
	 *            a movie object that represent the random movie
	 */
	public static void setRandomMovie(Movie randomMovie) {
		MovieController.randomMovie = randomMovie;
	}

	/**
	 * This method is to set the Random Movie generated from the database route
	 * 
	 * @param title,
	 *            a String that represent the movie's title
	 * @param genre,
	 *            a String that represent the movie's genre
	 * @param rating,
	 *            a float that represent the movie's rating
	 * @param min,
	 *            a float to set the min variable to generate a random movie from a
	 *            specific range
	 * @param max,
	 *            a float to set the min variable to generate a random movie from a
	 *            specific range
	 * @param count,
	 *            an int that represent the size of the result set.
	 */
	public void showMovie(String title, String genre, Float rating, Float min, Float max, int count) {
		this.movieName.setText("Title: " + title);
		this.locationName.setText("Rating : " + rating.toString());
		this.movieGenre.setText("Genre: " + genre);
		this.genre = genre;
		this.min = min;
		this.max = max;
		this.count = count;
	}

	/**
	 * This method regenerate a random movie from the DB route
	 */
	public void regenerate() {
		Connection connect = null;
		Statement stmt = null;
		ResultSet rs = null;
		String genre = this.genre;
		Float min = this.min, max = this.max;
		int count = this.count;
		String url = "jdbc:sqlite:imdb-movies.db";
		String sqlmovie = "SELECT ID, Title, Rating FROM movies WHERE Genre = " + "\"" + genre + "\" AND Rating >= "
				+ min + " AND Rating <= " + max;
		int random_int = 0;
		try {
			connect = DriverManager.getConnection(url);
			stmt = connect.createStatement();
			rs = stmt.executeQuery(sqlmovie);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			Random rand = new Random();
			random_int = rand.nextInt(count) + 1;
			for (int i = 1; i <= random_int; i++)
				rs.next();
			this.movieName.setText("Title: " + rs.getString(2));
			this.locationName.setText("Rating : " + rs.getFloat(3));
			this.movieGenre.setText("Genre: " + genre);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				connect.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
