package ooad.amazon.com.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ooad.amazon.com.bean.Advertisment;
import ooad.amazon.com.dao.AdvertisementDAO;

@Path("/AdvertismentController")
public class AdvertismentController {


	 @Path("/advertisments")
	 @GET
	 @Produces(MediaType.APPLICATION_JSON)
	    public Response getAdvertisments() {

		 GenericEntity<List<Advertisment>> ads;
	
		 ads  = new GenericEntity<List<Advertisment>>( AdvertisementDAO.getAllAdvertisments()) { };
		
		 return Response.ok(ads).build();
	
	    }
	
	
	
	
	
}
