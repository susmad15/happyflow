package com.ssischaefer.happyflow.rest.resource;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ssischaefer.happyflow.data.Package;
import com.ssischaefer.happyflow.database.proxy.PackageProxy;

@Path("/packageresource")
public class PackageResource {

	public static void insertPackage(String packageID, String palletID, int productID, int productAmount) {
		PackageProxy.insertPackage(packageID, palletID, productID, productAmount);
	}
	
	@DELETE
	@Path("/deletePackage/{id : .+}")
	public static void deletePackage(@PathParam("id") String packageID) {
		PackageProxy.deletePackage(packageID);
	}
	
	@POST
	@Path("/updateProductAmount/{id : .+}/{amount : .+}")
	public static void updateProductAmount(@PathParam("id") String packageID, @PathParam("amount") int productAmount) {
		PackageProxy.updateProductAmount(packageID, productAmount);
	}
	
	@GET
	@Path("/getPackage/{id : .+}")
	@Produces(MediaType.APPLICATION_JSON)
	public static Package getPackage(@PathParam("id") String packageID) {
		return PackageProxy.getPackage(packageID);
	}

	@GET
	@Path("/getPackageIDs/{id : .+}")
	@Produces(MediaType.APPLICATION_JSON)
	public static List<String> getPackageIDs(@PathParam("id") String palletID) {
		return PackageProxy.getPackageIDs(palletID);
	}

	@GET
	@Path("/getPalletIDs")
	@Produces(MediaType.APPLICATION_JSON)
	public static List<String> getPalletIDs() {
		return PackageProxy.getPalletIDs();
	}

	@GET
	@Path("/getProductAmount/{id : .+}")
	@Produces(MediaType.APPLICATION_JSON)
	public static int getProductAmount(@PathParam("id") String packageID) {
		return PackageProxy.getProductAmount(packageID);
	}
	
	@GET
	@Path("/getProductID/{id : .+}")
	@Produces(MediaType.APPLICATION_JSON)
	public static int getProductID(@PathParam("id") String packageID) {
		return PackageProxy.getProductID(packageID);
	}
}
