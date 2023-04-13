package com.cognixia.jumplus;

import java.util.ArrayList;



public class MovieList {
	
	private ArrayList<Movie> movies;

	public MovieList() {
		super();
		movies = new ArrayList<Movie>();
		movies.add(new Movie("Gattaca"));
		movies.add(new Movie("Clockwork Orange"));
		movies.add(new Movie("Mandy"));
		movies.add(new Movie("The Ring"));
		movies.add(new Movie("Flash Gordon"));
	}
	

	public boolean rateMovie(int index, double rating) {
		if(rating > 5 || rating < 0) {
			System.out.println("You have entered a rating outside of the range 0-5.");
			return false;
		}
		if(index < 0 || index > 4) {
			System.out.println("You have entered an invalid movie number.");
			return false;
		}
		Movie selected = movies.get(index);
		selected.setNumberOfRatings(selected.getNumberOfRatings() + 1);
		selected.setTotalRating(selected.getTotalRating() + rating);
		return true;
	}
	
	public void printMovies() {
		String [] col = {
				"Movie",
				"Avg. Rating",
				"# of Ratings" 
		};
		
		//Build data
		String[][] data = new String[5][3];	
		
		for(int i = 0; i < movies.size(); i++) {
			data[i][0] = movies.get(i).getTitle();
			data[i][1] = movies.get(i).getAverage();
			data[i][2] = String.valueOf(movies.get(i).getNumberOfRatings());
		}
		
		//Border
		System.out.print("+");
		for(int i = 0; i < 94; i++) {
			System.out.print("-");
		}
		System.out.print("+");
		System.out.println("");
		//Column Names
		System.out.format("%24s", "Selection Number");
		System.out.format("%24s",col[0]);
		System.out.format("%24s",col[1]);
		System.out.format("%24s",col[2]);
		System.out.println("");
		
		//Print data
		for(int i = 0; i < movies.size(); i++) {
			System.out.format("%24s", (i + 1) + ").");
			System.out.format("%24s", data[i][0]);
			System.out.format("%24s", data[i][1]);
			System.out.format("%24s", data[i][2]);
			System.out.println("");
		}
		System.out.print("+");
		for(int i = 0; i < 94; i++) {
			System.out.print("-");
		}
		System.out.print("+");
		System.out.println("");
		
	}
	
	
}
