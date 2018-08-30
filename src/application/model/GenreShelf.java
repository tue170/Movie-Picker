package application.model;

import java.util.ArrayList;
/**
 * 
 * @author Team Mediocre
 * Jennifer Nguyen(zfv431)
 * Tiffany Tsai (tue170)
 * Keegan Knisely (Har336)
 *
 */
public class GenreShelf {
	private String genreName;
	private ArrayList<Movie> movies;

	/**
	 * This is the constructor of the GenreShelf object
	 * 
	 * @param shelfIn,
	 *            a String that represent the genre in the shelf
	 */
	public GenreShelf(String shelfIn) {
		setGenreName(shelfIn);
		setMovies(new ArrayList<>());
	}

	/**
	 * This method is to retrive a Movie object from the Arraylist in the Genreshelf
	 * object.
	 * 
	 * @param index,
	 *            the position the Movie is in.
	 * @return a Movie Object that contains the movie title and movie location.
	 */
	public Movie getMovie(int index) {
		return this.movies.get(index);
	}

	/**
	 * This method is used to retrieve the class variable genreName.
	 * 
	 * @return a string that represent one of the nine genre
	 */
	public String getGenreName() {
		return genreName;
	}

	/**
	 * This method is used to set the genreName class variable
	 * 
	 * @param genreName,
	 *            a string that represent one of the nine genre
	 */
	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}

	/**
	 * This method is used to get the arrayList of movie for the genreShelf
	 * 
	 * @return an array list of movies
	 */
	public ArrayList<Movie> getMovies() {
		return movies;
	}

	/**
	 * This method is used to initialize the array list.
	 * 
	 * @param movies,
	 *            an array list of movies
	 */
	public void setMovies(ArrayList<Movie> movies) {
		this.movies = movies;
	}

	/**
	 * This method is used to print the GenreShelf object.
	 */
	@Override
	public String toString() {
		String temp = "";
		for (int i = 0; i < movies.size(); i++) {
			temp += "[genreName=" + genreName + ", movie=" + movies.get(i).getTitle() + " from"
					+ movies.get(i).getLocation() + "]\n";

		}

		return temp;
	}
}
