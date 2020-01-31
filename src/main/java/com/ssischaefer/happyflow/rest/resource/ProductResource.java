package com.ssischaefer.happyflow.rest.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ssischaefer.happyflow.data.Product;
import com.ssischaefer.happyflow.database.proxy.ProductProxy;

@Path("/productresource")
public class ProductResource {

	public static void changeProductAmount(String packageID, int productAmount) {
		ProductProxy.changeProductAmount(packageID, productAmount);
	}
	
	@GET
	@Path("/getProduct/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public static Product getProduct(@PathParam("id") int productID) {
		return ProductProxy.getProduct(productID);
	}
	
}
