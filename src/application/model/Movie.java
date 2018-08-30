package application.model;

/**
 * 
 * @author Team Mediocre
 * Jennifer Nguyen(zfv431)
 * Tiffany Tsai (tue170) Keegan
 * Knisely (Har336)
 *
 */

public class Movie {
	private String title;
	private String location;

	public Movie(String movieTitle, String movieLocation) {
		this.title = movieTitle;
		this.location = movieLocation;
	}

	public Movie(String movieTitle) {
		this.title = movieTitle;
		this.location = "Go Find it Yourself";
	}

	/**
	 * This method is used to retrive a movie title.
	 * 
	 * @return , a String that represent the movie title.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * This method is to set the class variable title to the passed in movie title.
	 * 
	 * @param title,
	 *            a String that represent the mvoie title.
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * This method is used to retrieve the location of the movie for CSV file.
	 * 
	 * @return , a String that represent the location of the movie.
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * This method is used to set the location of the movie
	 * 
	 * @param location,
	 *            a String that represent the location of the movie
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * This method is the toString of the object Movie.
	 */
	@Override
	public String toString() {

		// String temp = title.toLowerCase() + " " + location;
		return title.toLowerCase();
	}
}
