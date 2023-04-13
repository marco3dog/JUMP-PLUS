package com.cognixia.jumplus;

public class MovieRaterRunner {

	public static void main(String[] args) {

		MovieList movies = new MovieList();
		TopLevel.generateRandomUserList(5, movies);
		movies.printMovies();
		System.out.println("");
		TopLevel.printMainMenu();
		movies.printRateScreen(3);

	}
	

}
