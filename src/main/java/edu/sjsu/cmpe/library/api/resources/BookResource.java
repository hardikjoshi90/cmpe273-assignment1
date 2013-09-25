package edu.sjsu.cmpe.library.api.resources;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.HashMap;

import com.yammer.dropwizard.jersey.params.LongParam;
import com.yammer.metrics.annotation.Timed;

import edu.sjsu.cmpe.library.domain.Author;
import edu.sjsu.cmpe.library.domain.Book;
import edu.sjsu.cmpe.library.dto.BookDto;
import edu.sjsu.cmpe.library.dto.LinkDto;
import edu.sjsu.cmpe.library.dto.LinksDto;
@Path("/v1/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)



public class BookResource {

	public static long isbnId=1;
	public static long authorId=1;
	public static HashMap<Long,Book> bookRepositroryMap = new HashMap<Long,Book>();
	public static HashMap<Long,Author> authorRepositoryMap = new HashMap<Long,Author>();
	@POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
	
    public Response createBook(@Valid Book book)
	{
		LinksDto createBooklinkResponse = new LinksDto();
		bookRepositroryMap.put(isbnId,book);
		
		ArrayList<Author> authorList = new ArrayList<Author>();
		authorList = book.getAuthors();
		
		for(int i = 0;i<authorList.size();i++)
		{
			Author auth = (Author)authorList.get(i);

			auth.setAuthorId(authorId);
			auth.setIsbn(isbnId);
			
			authorRepositoryMap.put(authorId, auth);
			authorId++;
		}
		
		createBooklinkResponse.addLink(new LinkDto("view-book", "/books/"+isbnId, "GET"));
		createBooklinkResponse.addLink(new LinkDto("delete-book", "/books/"+isbnId, "DELETE"));
		createBooklinkResponse.addLink(new LinkDto("update-book", "/books/"+isbnId, "PUT"));
		createBooklinkResponse.addLink(new LinkDto("create-review", "/books/"+isbnId+"/reviews", "POST"));
		isbnId++;
		//return createBooklinkResponse;
		return Response.status(201).entity(createBooklinkResponse).build();
		
	}
	
	    
    @GET
    @Path("/{isbn}")
    @Timed(name = "view-book")
    public Response getBookByIsbnId(@PathParam("isbn") LongParam isbn) {
    
    Book book = new Book();
    book = bookRepositroryMap.get(isbn.get());
    book.setIsbn(isbn.get());
	BookDto bookResponse = new BookDto(book);	
	
	bookResponse.addLink(new LinkDto("view-book", "/books/" + book.getIsbn(),"GET"));
	bookResponse.addLink(new LinkDto("update-book","/books/" + book.getIsbn(), "PUT"));
	bookResponse.addLink(new LinkDto("delete-book","/books/" + book.getIsbn(), "DELETE"));
	bookResponse.addLink(new LinkDto("create-review","/books/" + book.getIsbn()+"/reviews", "POST"));
	bookResponse.addLink(new LinkDto("view-all-reviews","/books/" + book.getIsbn()+"/reviews", "GET"));

	//return bookResponse;
	return Response.status(200).entity(bookResponse).build();
    }
    
    @DELETE
    @Path("/{isbn}")
    @Timed(name = "delete-book")
    public Response deleteBookByIsbnId(@PathParam("isbn") LongParam isbn)
    {
    	LinksDto deleteLinkResponse = new LinksDto();
    	bookRepositroryMap.remove(isbn.get());
    	ReviewResource.reviewRepositoryMap.remove(isbn.get());
    	authorRepositoryMap.remove(isbn.get());
    	deleteLinkResponse.addLink(new LinkDto("create-book","/books/", "POST"));
    	isbnId--;
    	//return deleteLinkResponse;
    	return Response.status(200).entity(deleteLinkResponse).build();
    }
    
    @PUT
    @Path("/{isbn}")
    @Timed(name = "update-book")
    public Response updateBookByIsbnId(@PathParam("isbn") LongParam isbn,@QueryParam("status") String status)
    {
    	Book book = new Book();
    	book = bookRepositroryMap.get(isbn.get());
    	book.setStatus(status);
    	bookRepositroryMap.put(isbn.get(), book);
    	//bookMap.put(isbn.get(), book);
    	LinksDto updateBookResponse = new LinksDto();
    	updateBookResponse.addLink(new LinkDto("view-book","/books/"+isbn.get(), "GET"));
    	updateBookResponse.addLink(new LinkDto("update-book","/books/"+isbn.get(), "PUT"));
    	updateBookResponse.addLink(new LinkDto("delete-book","/books/"+isbn.get(), "DELETE"));
    	updateBookResponse.addLink(new LinkDto("create-book","/books/"+isbn.get(), "POST"));
    	updateBookResponse.addLink(new LinkDto("view-all-reviews","/books/"+isbn.get(), "GET"));
    	//return updateBookResponse;
    	return Response.status(200).entity(updateBookResponse).build();
    }
    
}

