package edu.sjsu.cmpe.library.api.resources;

import java.util.ArrayList;
import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.yammer.dropwizard.jersey.params.LongParam;

import edu.sjsu.cmpe.library.domain.Review;
import edu.sjsu.cmpe.library.dto.LinkDto;
import edu.sjsu.cmpe.library.dto.LinksDto;
import edu.sjsu.cmpe.library.dto.ReviewDto;


@Path("/v1/books/{isbn}/reviews")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ReviewResource {
	public static HashMap<Long,Review> reviewRepositoryMap = new HashMap<Long,Review>();
	public static long reviewId=1;
	@POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createReview(Review review,@PathParam("isbn") LongParam isbn)
	{
		review.setIsbn(isbn.get());
		review.setReviewId(reviewId);
		reviewRepositoryMap.put(reviewId, review);
		LinksDto reviewResponse = new LinksDto();
		
		reviewResponse.addLink(new LinkDto("view-review", "/books/"+isbn.get()+"/review/"+reviewId,
			"GET"));
		reviewId++;
		//return reviewResponse;
		return Response.status(201).entity(reviewResponse).build();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
	public Response getReviewById(@PathParam("isbn") LongParam isbn, @PathParam("id") LongParam reviewId)
	
	{
		Review review = new Review();
		review.setIsbn(isbn.get());
		review.setReviewId(reviewId.get());
		review = reviewRepositoryMap.get(reviewId.get());
		ReviewDto reviewResponse = new ReviewDto(review);
		
		reviewResponse.addLink(new LinkDto("view-review", "/books/"+isbn.get()+"/review/"+review.getReviewId(),
			"GET"));
		return Response.status(200).entity(reviewResponse).build();
		//return reviewResponse;
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
	public Response getAllReviews(@PathParam("isbn") LongParam isbn)
	{
		ArrayList<Review> reviewList = new ArrayList<Review>();
		for(long i=1;i<=reviewRepositoryMap.size();i++)
		{
			if(reviewRepositoryMap.get(i).getIsbn() == isbn.get())
			{
				reviewList.add(reviewRepositoryMap.get(i));
			}
		}
		return Response.status(200).entity(reviewList).build();
		//return reviewList;
	}	
}
