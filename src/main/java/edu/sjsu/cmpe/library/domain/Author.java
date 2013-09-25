package edu.sjsu.cmpe.library.domain;

public class Author {
	
	private long authorId;
	private String name;
	public long isbn;
	

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public long getAuthorId() {
		
		return authorId;
	}
	public void setAuthorId(long authorId) {
		this.authorId = authorId;
	}
	public long getIsbn() {
		return isbn;
	}
	public void setIsbn(long isbn) {
		this.isbn = isbn;
	}
	/*public ArrayList<String> getAuthors() {
		System.out.println("GET Authors");
		return authors;
	}
	public void setAuthors(ArrayList<String> authors) {
		System.out.println("SET Authors");
		
		this.authors = authors;
		System.out.println(authors);
	}*/
	
	
	
}
