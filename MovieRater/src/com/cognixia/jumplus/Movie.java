package com.cognixia.jumplus;

import java.text.DecimalFormat;

public class Movie {

	private String title;
	private double totalRating;
	private int numberOfRatings;
	
	public Movie(String title) {
		super();
		this.title = title;
		totalRating = 0;
		numberOfRatings = 0;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getTotalRating() {
		return totalRating;
	}

	public void setTotalRating(double totalRating) {
		this.totalRating = totalRating;
	}

	public int getNumberOfRatings() {
		return numberOfRatings;
	}

	public void setNumberOfRatings(int numberOfRatings) {
		this.numberOfRatings = numberOfRatings;
	}
	
	public String getAverage() {
		if(numberOfRatings == 0) {
			return ("N/A");
		}
		else {
			DecimalFormat df = new DecimalFormat("#.0");
			return df.format(totalRating/numberOfRatings);
		}
	}
	
	
}
