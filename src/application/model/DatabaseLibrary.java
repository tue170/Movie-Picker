package application.model;

public class DatabaseLibrary {
	private String movieTitle;
	private String movieGenre;
	private String date;
	private int Id;
	private int budget;
	private int length;
	private double rating;
	
	public DatabaseLibrary(String movieTitle, String movieGenre, String date, int id, int budget, int length, double rating) {
		this.movieTitle = movieTitle;
		this.movieGenre = movieGenre;
		this.date = date;
		Id = id;
		this.budget = budget;
		this.length = length;
		this.rating = rating;
	}
	public String getMovieTitle() {
		return this.movieTitle;
	}
	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}
	public String getMovieGenre() {
		return this.movieGenre;
	}
	public void setMovieGenre(String movieGenre) {
		this.movieGenre = movieGenre;
	}
	public String getDate() {
		return this.date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getId() {
		return this.Id;
	}
	public void setId(int id) {
		this.Id = id;
	}
	public int getBudget() {
		return this.budget;
	}
	public void setBudget(int budget) {
		this.budget = budget;
	}
	public int getLength() {
		return this.length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public double getRating() {
		return this.rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	
}
