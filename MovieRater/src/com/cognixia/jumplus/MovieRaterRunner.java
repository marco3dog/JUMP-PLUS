package com.cognixia.jumplus;

public class MovieRaterRunner {

	public static void main(String[] args) {

		UserList users = new UserList();
		users.addUser("fake@ema.com", "pass");
		users.addUser("fake@ema.com", "pass");
		users.printSize();
		System.out.println(users.toString());
		MovieList movies = new MovieList();
		movies.printMovies();

	}

}
