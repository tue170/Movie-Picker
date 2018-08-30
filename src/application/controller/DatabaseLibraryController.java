package application.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Random;
import java.util.ResourceBundle;
import application.Main;
import application.model.DatabaseLibrary;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * The DatabaseController class handle any events that occur when the user
 * interacts with DataBaseLib.fxml.
 * 
 * @author Tiffany Tsai(tue170)
 *
 */
public class DatabaseLibraryController implements Initializable {

	@FXML
	Button submitButton;
	@FXML
	Button generateButton;
	@FXML
	private TableView<DatabaseLibrary> movieTable;
	@FXML
	private TableColumn<DatabaseLibrary, String> genreCol;
	@FXML
	private TableColumn<DatabaseLibrary, String> titleCol;
	@FXML
	private TableColumn<DatabaseLibrary, String> dateCol;
	@FXML
	private ComboBox<String> genreBox;
	@FXML
	private ComboBox<String> generateGenre;
	@FXML
	private ComboBox<Float> ratingMin;
	@FXML
	private ComboBox<Float> ratingMax;
	@FXML
	private TextField titleTextField;
	@FXML
	private DatePicker releaseDate;
	@FXML
	private Label genreLabel;
	@FXML
	private Label titleLabel;
	@FXML
	private Label ratingLabel;
	@FXML
	private Label dateLabel;
	@FXML
	private Label dateLabel2;
	@FXML
	private Label generateLabel;
	@FXML
	private Text genreError;
	@FXML
	private Text titleError;
	@FXML
	private Text dateError;
	@FXML
	private Text genreError1;
	@FXML
	private Text ratingError;
	@FXML
	private Text noMovie;
	@FXML
	private Text minGreater;
	@FXML
	private Text movieExist;
	@FXML
	ImageView background;

	public ObservableList<DatabaseLibrary> movieList;
	public ObservableList<String> genreList;
	public ObservableList<Float> ratingList;

	/**
	 * Method called by the FXML loader after the GUI components declared above is
	 * are injected
	 */
	public void initialize(URL url, ResourceBundle rb) {
		MainController.database = true;
		parseGenre();
		reset();
		Image image;
		try {
			image = new Image(new FileInputStream("../MoviePicker/src/application/view/images/library.jpg"));
			background.setImage(image);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		genreBox.setPromptText("Select a genre");
		generateGenre.setPromptText("Select a genre");
		this.genreBox.setItems(genreList);
		this.generateGenre.setItems(genreList);
		this.genreBox.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				populateData();
			}
		});
		populateRating();
	}

	/**
	 * The method display main.Fxml.
	 */
	public void showMain() {
		Main.stage.setScene(Main.scene);
		Main.stage.show();
	}

	/**
	 * Method that populate the table view in Databaselib.fxml.
	 */
	public void fillTable() {
		this.genreCol.setCellValueFactory(new PropertyValueFactory<DatabaseLibrary, String>("movieGenre"));
		this.titleCol.setCellValueFactory(new PropertyValueFactory<DatabaseLibrary, String>("movieTitle"));
		this.dateCol.setCellValueFactory(new PropertyValueFactory<DatabaseLibrary, String>("date"));
		this.movieTable.setItems(movieList);
	}

	/**
	 * Method which get the information that is going to be display on the table
	 * view in Databaselib.fxml from the database.
	 */
	public void populateData() {
		String genre = this.genreBox.getValue();
		if (genre.equals(this.genreBox.getPromptText()) || genre == null)
			return;
		Connection connect = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT Title, Genre, DateReleased FROM movies WHERE Genre = \"" + genre + "\"";
		String url = "jdbc:sqlite:imdb-movies.db";
		try {
			connect = DriverManager.getConnection(url);
			stmt = connect.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		parseResultOfAllMovies(rs);
		fillTable();
		try {
			stmt.close();
			connect.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Method that split the data in the result set that contains all movies in the
	 * database by their title, genre, and data released; then store the data into
	 * the list of movies.
	 * 
	 * @param rs
	 *            Result set of all movies in the database
	 */
	public void parseResultOfAllMovies(ResultSet rs) {
		try {
			movieList = FXCollections.observableArrayList();
			while (rs.next()) {
				this.movieList.add(new DatabaseLibrary(rs.getString(1), rs.getString(2), rs.getString(3), 0, 0, 0, 0));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Method which get all the genre of the movies in the database and store then
	 * in ArrayList.
	 */
	public void parseGenre() {
		Connection connect = null;
		Statement stmt = null;
		ResultSet rs = null;
		genreList = FXCollections.observableArrayList();
		String sql = "SELECT DISTINCT Genre FROM movies ";
		String url = "jdbc:sqlite:imdb-movies.db";
		try {
			connect = DriverManager.getConnection(url);
			stmt = connect.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			while (rs.next()) {
				genreList.add(rs.getString(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				connect.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Method which get all the rating of the movies in the database and store them
	 * in ArrayList then populate the combo box in DataBasLib.fxml
	 */
	public void populateRating() {
		ratingList = FXCollections.observableArrayList();
		for (int i = 1; i <= 10; i++) {
			this.ratingList.add((float) i);
		}
		this.ratingMin.setItems(this.ratingList);
		this.ratingMax.setItems(this.ratingList);
		this.ratingMin.getSelectionModel().select(0);
		;
		this.ratingMax.getSelectionModel().select(9);

	}

	/**
	 * Method that check if the user input for movie genre, title, and released date
	 * is valid or not.
	 */
	public void addMovieError() {
		String genre = this.genreBox.getValue();
		String title = this.titleTextField.getText();
		LocalDate date = this.releaseDate.getValue();
		if (genre == null) {
			setError(this.genreLabel, this.genreError);
			return;
		}
		if (genre != null) {
			this.genreLabel.setStyle("-fx-text-fill: white");
			this.genreError.setVisible(false);
		}
		if (title.isEmpty()) {
			setError(this.titleLabel, this.titleError);
			return;
		}
		if (!(title.isEmpty())) {
			this.titleLabel.setStyle("-fx-text-fill: white");
			this.titleError.setVisible(false);
		}
		if (date == null) {
			this.dateLabel.setStyle("-fx-text-fill: red");
			setError(this.dateLabel2, this.dateError);
			return;
		}
		if (date != null) {
			this.dateLabel.setStyle("-fx-text-fill: white");
			this.dateLabel2.setStyle("-fx-text-fill: white");
			this.dateError.setVisible(false);
		}
		if (genre != this.genreBox.getPromptText() && !(title == null || title.isEmpty()) && date.toString() != null) {
			reset();
			for (DatabaseLibrary d : this.movieList) {
				if (d.getMovieTitle().equalsIgnoreCase(title)) {
					this.movieExist.setVisible(true);
					return;
				}
			}
			addMovie();
			System.out.println("Movie title: " + title + " Date :" + date + " Genre :" + genre);
		}

	}

	/**
	 * Method that change the color of the label and display the error message when
	 * the input is invalid.
	 * 
	 * @param label
	 *            The label GUI component in DatabaseLib.fxml
	 * @param text
	 *            The Text GUI component in DatabaseLib.fxml
	 */
	public void setError(Label label, Text text) {
		label.setStyle("-fx-text-fill: red");
		text.setVisible(true);
	}

	/**
	 * Method that changes label color to default color and set error message
	 * invisible once the user correct the invalid input.
	 */
	public void reset() {
		this.genreLabel.setStyle("-fx-text-fill: white");
		this.genreError.setVisible(false);
		this.titleLabel.setStyle("-fx-text-fill: white");
		this.titleError.setVisible(false);
		this.dateLabel.setStyle("-fx-text-fill: white");
		this.dateLabel2.setStyle("-fx-text-fill: white");
		this.dateError.setVisible(false);
		this.ratingLabel.setStyle("-fx-text-fill: white");
		this.ratingError.setVisible(false);
		this.movieExist.setVisible(false);
		this.genreError1.setVisible(false);
		this.minGreater.setVisible(false);
		this.noMovie.setVisible(false);

	}

	/**
	 * Method that get the total number of movies in the data base and add movie to
	 * the Data base.
	 */
	public void addMovie() {
		int size = 0;
		Connection connect = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT Count(ID) FROM movies";
		String url = "jdbc:sqlite:imdb-movies.db";
		try {
			connect = DriverManager.getConnection(url);
			stmt = connect.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			rs.next();
			size = rs.getInt(1);
			System.out.println(size);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				connect.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		writeIntoDataBase(size);

	}

	/**
	 * Method that insert the movie into database.
	 * 
	 * @param size
	 *            Total number of movies in the data base
	 */
	public void writeIntoDataBase(int size) {
		String genre = this.genreBox.getValue();
		String title = this.titleTextField.getText();
		LocalDate date = this.releaseDate.getValue();
		Connection connect = null;
		Statement stmt = null;
		String sqladd = "INSERT INTO movies (ID, Budget, DateReleased, Min_Length, Title, Rating, Genre) " + "VALUES ("
				+ size + 1 + ", null, \"" + date + "\", null, \"" + title + "\", 1.0, \"" + genre + "\");";
		String url = "jdbc:sqlite:imdb-movies.db";
		try {
			connect = DriverManager.getConnection(url);
			stmt = connect.createStatement();
			stmt.execute(sqladd);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				connect.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.titleTextField.setText("");
		this.releaseDate.getEditor().clear();
	}

	/**
	 * Method that randomly generate a  movie from data base
	 */
	public void generateMovie() {
		String genre = this.generateGenre.getValue();
		Float min = this.ratingMin.getValue(), max = this.ratingMax.getValue();
		int count = 0;
		Connection connect = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT Count(ID) FROM movies WHERE Genre = " + "\"" + genre + "\" AND Rating >= " + min
				+ " AND Rating <= " + max;
		String url = "jdbc:sqlite:imdb-movies.db";
		try {
			connect = DriverManager.getConnection(url);
			stmt = connect.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			rs.next();
			count = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			getResultSet(count);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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

	/**
	 * Method that change the color of the labels and display error message if the
	 * user puts in an invalid input.
	 */
	public void generateError() {
		String genre = this.generateGenre.getValue();
		Float min = this.ratingMin.getValue(), max = this.ratingMax.getValue();
		if (genre == null) {
			setError(this.generateLabel, this.genreError1);
			return;
		}
		if (genre != null) {
			this.generateLabel.setStyle("-fx-text-fill: white");
			this.genreError1.setVisible(false);
		}
		if (min == null) {
			setError(this.ratingLabel, this.ratingError);
			return;
		}
		if (max == null) {
			setError(this.ratingLabel, this.ratingError);
			return;
		}
		if (min != null && max != null) {
			this.ratingLabel.setStyle("-fx-text-fill: white");
			this.ratingError.setVisible(false);
			if (min > max) {
				setError(this.ratingLabel, this.minGreater);
				return;
			}
			if (max < min) {
				setError(this.ratingLabel, this.minGreater);
				return;
			}
			if (max > min) {
				this.ratingLabel.setStyle("-fx-text-fill: white");
				this.minGreater.setVisible(false);
			}
		}
		if (genre != null && min != null && max != null && max > min) {
			generateMovie();
		}

	}

	/**
	 * Method that get a set of movies based on the genre and rating range that user
	 * input in, and randomly generate a movie from the set.
	 * 
	 * @param count
	 *            Total number of movies inside of the genre user picked
	 * @throws SQLException
	 */
	public void getResultSet(int count) throws SQLException {
		String genre = this.generateGenre.getValue();
		Float min = this.ratingMin.getValue(), max = this.ratingMax.getValue();
		String title = null;
		Float rating = null;
		int movie_ID = 0, random_int = 0;
		Connection connect = null;
		Statement stmt = null;
		ResultSet rs_movie = null;
		String sqlmovie = "SELECT ID, Title, Rating FROM movies WHERE Genre = " + "\"" + genre + "\" AND Rating >= "
				+ min + " AND Rating <= " + max;
		String url = "jdbc:sqlite:imdb-movies.db";
		try {
			connect = DriverManager.getConnection(url);
			stmt = connect.createStatement();
			rs_movie = stmt.executeQuery(sqlmovie);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (!rs_movie.isBeforeFirst()) {
			this.noMovie.setVisible(true);
			return;
		}
		try {
			Random rand = new Random();
			random_int = rand.nextInt(count) + 1;
			for (int i = 1; i <= random_int; i++)
				rs_movie.next();
			movie_ID = rs_movie.getInt(1);
			title = rs_movie.getString(2);
			rating = rs_movie.getFloat(3);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs_movie.close();
				stmt.close();
				connect.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		getMovie(title, genre, rating, min, max, count);

	}

	/**
	 * Method that displayed MoviePop.fxml and call the method from MovieController
	 * to displayed the movie information that's randomly generated from
	 * getResultSet method. Also passes the rating range and genre that user choose
	 * from DatabaseLib.fxml to the regenerate method for regenerating purpose.
	 * 
	 * @param title
	 *            Title of the movie
	 * @param genre
	 *            Genre of the movie
	 * @param rating
	 *            Ration of the movie
	 * @param min
	 *            Rating minimum that user entered
	 * @param max
	 *            Rating maximum that user entered
	 * @param count
	 *            Total number of movie that's inside of the genre user picked
	 */
	public void getMovie(String title, String genre, Float rating, Float min, Float max, int count) {
		AnchorPane root = null;
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(Main.class.getResource("../application/view/MoviePop.fxml"));
		try {
			root = (AnchorPane) fxmlLoader.load();

		} catch (IOException e1) {
			e1.printStackTrace();
		}
		MovieController controller = fxmlLoader.<MovieController>getController();
		controller.showMovie(title, genre, rating, min, max, count);
		Scene scene = new Scene(root);
		Main.stage.setScene(scene);
		Main.stage.show();

	}
}
