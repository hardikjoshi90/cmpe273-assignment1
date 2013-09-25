package edu.sjsu.cmpe.library.dto;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import edu.sjsu.cmpe.library.domain.Author;

@JsonPropertyOrder(alphabetic = true)
public class AuthorDto extends LinksDto{
	
	private Author author;

    /**
     * @param Author
     */
    
    public AuthorDto(Author author) {
	super();
	this.author = author;
    }

    /**
     * @return the Author
     */
    public Author getAuthor() {
	return author;
    }

    /**
     * @param Author
     *            the Author to set
     */
    public void setAuthor(Author author) {
	this.author = author;
    }

}
