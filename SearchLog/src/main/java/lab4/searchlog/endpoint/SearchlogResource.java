/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4.searchlog.endpoint;

import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import lab4.searchlog.business.SearchLog;

/**
 * REST Web Service
 *
 * @author student
 */
@Path("searchlog")
public class SearchlogResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of SearchlogResource
     */
    public SearchlogResource() {
    }

    /**
     * PUT method for updating or creating an instance of SearchlogResource
     * @param movieid
     * @param email
     */
    @POST
    //@Consumes(MediaType.APPLICATION_XML)
    @Path("/{movieid}+{email}")
    public void putXml(@PathParam("movieid") int movieid, @PathParam("email") String email) {
        SearchLog s= new SearchLog();
        System.out.println(movieid + " "+ email);
        System.out.println("Here 3");
        s.addSearch(movieid, email);
    }
}
