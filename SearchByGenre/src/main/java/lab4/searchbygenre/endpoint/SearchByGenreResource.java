/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4.searchbygenre.endpoint;

import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import lab4.searchbygenre.business.SearchByGenre;
import lab4.searchbygenre.helper.MoviesXML;

/**
 * REST Web Service
 *
 * @author student
 */
@Path("searchByGenre/{genre}")
public class SearchByGenreResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of SearchByGenreResource
     */
    public SearchByGenreResource() {
    }

    /**
     * Retrieves representation of an instance of lab4.searchbygenre.endpoint.SearchByGenreResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_XML) 
    public String getXml(@PathParam("genre") String genre) {
        SearchByGenre search= new SearchByGenre();
        MoviesXML movies = search.searchByGenre(genre);
        
        System.out.println(">>>>>>>>>>>>>>>>>>" + movies);
        
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(MoviesXML.class);
        
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(movies, sw);

            return (sw.toString());

        }catch(JAXBException ex) {
            Logger.getLogger(SearchByGenreResource.class.getName()).log(Level.SEVERE, null, ex);
            return("error happened");
        }
    }

    /**
     * PUT method for updating or creating an instance of SearchByGenreResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
}
