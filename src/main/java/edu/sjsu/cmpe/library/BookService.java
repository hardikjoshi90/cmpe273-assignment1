package edu.sjsu.cmpe.library;


import edu.sjsu.cmpe.library.domain.*;
public class BookService {
	
	
	public static BookService bookService = new BookService();
	
    public void CreateBook(Book book)
    {
    	
    	System.out.println("Inside Service class ");
    }

    
    

	public static BookService getInstance() {
		// TODO Auto-generated method stub
		return bookService;
	}
    
    
    
}
