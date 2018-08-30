package application.model;

import java.io.BufferedReader;
import java.io.File;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * 
 * @author Team Mediocre 
 * Jennifer Nguyen(zfv431) 
 * Tiffany Tsai (tue170) Keegan
 * Knisely (Har336)
 *
 */
public class Library {

	private ObservableList<String> files;
	private ArrayList<GenreShelf> shelves;

	public Library() {
		setShelves(new ArrayList<GenreShelf>());
		setFiles(FXCollections.observableArrayList());
		showFiles();
	}

	/**
	 * This method it to show the all the file in the resources folder in the
	 * project. Notice that there is a created folder where the movie are stored on
	 * under the src folder the current directory points to the user working
	 * directory aka where the project is working from plus the concatate string
	 * which will lead to the resource folder
	 */
	public void showFiles() {

		File currentDirectory = new File(System.getProperty("user.dir") + "\\src\\resources\\");

		File[] filesList = currentDirectory.listFiles();
		for (File f : filesList) {
			if (f.isFile()) {
				getFiles().add(f.getName());
				// System.out.println(f.getName());
			}
		}

	}

	/**
	 * This method is to grab a random Movie
	 * 
	 * @return Movie, movie which contains the the title and the location
	 */
	public Movie grabMovie() {
		int shelfIndex = (int) (Math.random() * 9);
		Movie movie;
		// System.out.println(shelfIndex);
		if (this.getShelves().get(shelfIndex).getMovies().isEmpty()) {
			movie = grabMovie();
		} else {
			movie = getShelves().get(shelfIndex)
					.getMovie((int) (Math.random() * this.getShelves().get(shelfIndex).getMovies().size()));
		}
		return movie;
	}

	/**
	 * This method is to grab a random Movie, an overload method.
	 * 
	 * @param shelf,
	 *            this allow a random movie to be produce within a certain genre
	 *            shelf.
	 * @return Movie, movie which contains the the title and the location
	 */
	public Movie grabMovie(String shelf) {
		Movie movie = null;
		int shelfIndex = -1;
		// System.out.println("GENRE:" + shelf);
		for (int i = 0; i < this.getShelves().size(); i++) {
			// System.out.println("SEARCHING" + getShelves().get(i).getGenreName());
			if ((shelf.toLowerCase()).contains((this.getShelves().get(i).getGenreName()))) {
				shelfIndex = i;
				break;
			}
		}
		// System.out.println("FOUND INDEX" + shelfIndex);
		movie = this.getShelves().get(shelfIndex)
				.getMovie((int) (Math.random() * this.getShelves().get(shelfIndex).getMovies().size()));

		return movie;
	}

	/**
	 * This method is used to parse the file and store the the movie name and
	 * location in a movie object which is store in the genre shelf according to the
	 * the Genre. (add in the movie arraylist in the genre shelf object)
	 * 
	 * @param filename,
	 *            represent the file na
	 * @throws IOException,
	 *             represent an IO exeption that can occur
	 */
	public void parseSelectedFile(String filename) throws IOException {
		for (int i = 0; i < 9; i++) {
			GenreShelf newShelf = new GenreShelf(Main.shelves[i]);
			// System.out.println(Main.shelves[i]);

			getShelves().add(newShelf);
		}
		refresh();
		File file = new File(System.getProperty("user.dir") + "\\src\\resources\\" + filename.trim());

		BufferedReader buffer = new BufferedReader(new FileReader(file));
		String temp = null;
		while ((temp = buffer.readLine()) != null) {
			String[] lineSplit = temp.split(",");
			switch (lineSplit[0].toLowerCase()) {
			case "action":

				// System.out.println("Testing " + lineSplit[0]);
				addToShelf(0, lineSplit);
				break;
			case "children":
				addToShelf(1, lineSplit);
				break;
			case "comedy":
				addToShelf(2, lineSplit);
				break;
			case "drama":
				addToShelf(3, lineSplit);
				break;
			case "fantasy":
				addToShelf(4, lineSplit);
				break;
			case "horror":
				addToShelf(5, lineSplit);
				break;
			case "musical":
				addToShelf(6, lineSplit);
				break;
			case "romance":
				addToShelf(7, lineSplit);
				break;
			default: // others
				addToShelf(8, lineSplit);
				break;
			}

		}
		// buffer.flush();
		buffer.close();

	}

	/**
	 * This method it to clear the array when file needs to be read again. clear the
	 * listview of duplicate data
	 */
	private void refresh() {
		for (int i = 0; i < 9; i++) {
			if (!getShelves().get(i).getMovies().isEmpty())
				getShelves().get(i).getMovies().clear();
		}
		// buffer.flush();
		// buffer.close();

	}

	/**
	 * This method is used as a sub method to add the movies in to the correct
	 * shelf.
	 * 
	 * @param index
	 *            , represent the index in which the genre is located in the shelves
	 * @param strArr
	 *            , the string that contain the movie title and location.
	 */
	public void addToShelf(int index, String[] strArr) {
		// TODO: Error Catching
		if (strArr.length < 3) {
			this.getShelves().get(index).getMovies().add(new Movie(strArr[1]));
			// System.out.println(getShelves().get(index).getMovies().toString());
		} else {
			this.getShelves().get(index).getMovies().add(new Movie(strArr[1], strArr[2]));
		}
	}

	/**
	 * This method is to get the observable list of string called files
	 * 
	 * @return , an observable list of strings
	 */
	public ObservableList<String> getFiles() {
		return files;
	}

	/**
	 * This method is to set the observablelist
	 * 
	 * @param files
	 *            , represent an observable list of string
	 */
	public void setFiles(ObservableList<String> files) {
		this.files = files;
	}

	/**
	 * This method is to get the arraylist of GenreShelf
	 * 
	 * @return , an arraylist that represent the shelf of genre
	 */
	public ArrayList<GenreShelf> getShelves() {
		return shelves;
	}

	/**
	 * This method is to set the genreShelf
	 * 
	 * @param shelves
	 *            represent the genre shelf
	 */
	public void setShelves(ArrayList<GenreShelf> shelves) {
		this.shelves = shelves;
	}

	/**
	 * This method is used to display the library Object.
	 */
	@Override
	public String toString() {
		return "Library [files=" + files + ", shelves=" + shelves + "]";
	}
}
