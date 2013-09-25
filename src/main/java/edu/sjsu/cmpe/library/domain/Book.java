package edu.sjsu.cmpe.library.domain;

import java.util.ArrayList;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yammer.dropwizard.validation.ValidationMethod;


public class Book {
	@JsonProperty
	@NotEmpty
    private long isbn;
    private String title;
    @JsonProperty("publication-date") private String publicationDate;
    private String language;
    @JsonProperty("num-pages") private int pages;
    private String status;    
    private ArrayList<Author> authors= new ArrayList<Author>();
    private ArrayList<Review> reviews = new ArrayList<Review>() ;
    
    @ValidationMethod(message="title can't be blank")
    
    public boolean isNotCoda() {
        return !("Coda".equals(title));
    }
    
    
	public ArrayList<Review> getReviews() {
		return reviews;
	}
	public void setReviews(ArrayList<Review> reviews) {
		this.reviews = reviews;
	}
	public ArrayList<Author> getAuthors() {
		return authors;
	}
	public void setAuthors(ArrayList<Author> authors) {
		this.authors = authors;
	}
	/*public long getAuthorId() {
		return authorId;
	}
	public void setAuthorId(long authorId) {
		this.authorId = authorId;
	}*/
	public long getIsbn() {
		return isbn;
	}
	public void setIsbn(long isbn) {
		this.isbn = isbn;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPublicationDate() {
		return publicationDate;
	}
	public void setPublicationDate(String publicationDate) {
		this.publicationDate = publicationDate;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
    // add more field here

    
}
