package edu.sjsu.cmpe.library.api.resources;

import java.util.ArrayList;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.yammer.dropwizard.jersey.params.LongParam;

import edu.sjsu.cmpe.library.domain.Author;
import edu.sjsu.cmpe.library.dto.AuthorDto;
import edu.sjsu.cmpe.library.dto.LinkDto;

@Path("/v1/books/{isbn}/authors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthorResource {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
	public Response getAuthors(@PathParam("isbn") LongParam isbn)
	
	{
		ArrayList<Author> authorResponse = new ArrayList<Author>();
		for(long i=1;i<=BookResource.authorRepositoryMap.size();i++)
		{
			if(BookResource.authorRepositoryMap.get(i).getIsbn() == isbn.get())
			{
				authorResponse.add(BookResource.authorRepositoryMap.get(i));
			}
		}
		
		//return authorResponse;
		return Response.status(200).entity(authorResponse).build();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
	public Response getAuthorsById(@PathParam("isbn") LongParam isbn, @PathParam("id") LongParam authorId)
	
	{
		Author authorByIdObject = new Author();
		authorByIdObject.setIsbn(isbn.get());
		authorByIdObject = BookResource.authorRepositoryMap.get(authorId.get());
		AuthorDto authorResponse = new AuthorDto(authorByIdObject);
		authorResponse.addLink(new LinkDto("view-review", "/books/"+isbn.get()+"/review/"+authorId.get(),
			"GET"));
	
		//return authorResponse;
		return Response.status(200).entity(authorResponse).build();
	}
	
}
