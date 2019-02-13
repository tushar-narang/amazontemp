package ooad.amazon.com.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import ooad.amazon.com.bean.Category;
import ooad.amazon.com.bean.Customer;
import ooad.amazon.com.bean.Order;
import ooad.amazon.com.bean.Product;
import ooad.amazon.com.bean.ProductImages;
import ooad.amazon.com.dao.OrderDAO;
import ooad.amazon.com.dao.ProductDAO;

@Path("/OrderController")
public class OrderController {

	 	@POST 
		@Path("/saveorder")
		@Consumes(MediaType.MULTIPART_FORM_DATA)
		public Response registercus(
				@FormDataParam("customerid") int custid,
				@FormDataParam("productid") int prodid,
				@FormDataParam("sellerid") int sellerid,
				@FormDataParam("quantity") int quantity){
			
	 		List<Integer> sellers = new ArrayList<>();
	 		sellers.add(sellerid);
	 		List<Integer> prods = new ArrayList<>();
	 		prods.add(prodid);
	 		List<Integer> quants = new ArrayList<>();
	 		quants.add(quantity);
	 		String op = OrderDAO.saveOrder(custid, prods, quants);
	 		if(op.equals("Insufficient Balance"))
	 			return Response.status(404).entity(op).build();
	 		else
	 			return Response.status(201).entity(op).build();
			
		}
	 	
	 	@GET 
		@Path("/getOrdersOfSeller/{sellerid}")
	 	@Produces(MediaType.APPLICATION_JSON)
		public Response getOrders(
				@PathParam("sellerid") int sellerid){
			
	 		
	 		List<Order> op = OrderDAO.getOrdersofSeller(sellerid);
	 		 GenericEntity<List<Order>> ops  = new GenericEntity<List<Order>>(op){};
	 			return Response.status(201).entity(ops).build();
			
		}
	 	
		@POST 
		@Path("/shipItem")
		@Consumes(MediaType.MULTIPART_FORM_DATA)
		public Response shipOrders(
				@FormDataParam("sellerid") int sellerid,
				@FormDataParam("orderid") int orderid){
			
	 		
	 			OrderDAO.confirmOrder(sellerid, orderid);
	 		
	 			return Response.status(201).entity("done").build();
			
		}
}
