package edu.sjsu.cmpe.library.domain;

public class Review {
	private long reviewId;
	private double rating;
	private String comment;
	public long isbn;
	
	public long getReviewId() {
		return reviewId;
	}
	public void setReviewId(long reviewId) {
		this.reviewId = reviewId;
	}
	
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public long getIsbn() {
		return isbn;
	}
	
	public void setIsbn(Long isbn) {
		this.isbn = isbn;// TODO Auto-generated method stub
		
	}
	
	
		// TODO Auto-generated method stub
	
	

}
