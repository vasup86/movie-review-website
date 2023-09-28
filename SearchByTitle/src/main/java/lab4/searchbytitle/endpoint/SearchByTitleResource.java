/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4.searchbytitle.endpoint;

import com.mysql.cj.jdbc.Blob;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import lab4.searchbytitle.business.SearchByTitle;
import lab4.searchbytitle.helper.MovieXML;
import lab4.searchbytitle.helper.MovieNull;
/**
 * REST Web Service
 *
 * @author student
 */
@Path("searchByTitle/{query}+{useremail}")
public class SearchByTitleResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public SearchByTitleResource() {
    }

    /**
     * Retrieves representation of an instance of lab4.searchbytitle.endpoint.GenericResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    public String getXml(@PathParam("query") String query, @PathParam("useremail") String email) {
        System.out.println("Here");
        query = query.replace("-", " ");
        System.out.println(query);
        
        SearchByTitle search= new SearchByTitle();
        MovieXML movie = search.search(query, email);
        
        System.out.println(">>>>>>>>>>>>>>>>>>" + movie);
        
        JAXBContext jaxbContext;
        
        // add to search log if success
        if(movie.getStatus().equals("success")){
            try{
                String con = System.getenv("searchlogservice").strip();
                String s = "http://"+ con +"/SearchLog/webresources/searchlog/" + movie.getMovieId() + "+" + email;
                URL url = new URL(s);
                System.out.println("here2");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");

                try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    String inputLine;
                    StringBuilder res = new StringBuilder();

                    while ((inputLine = in.readLine()) != null) {
                        res.append(inputLine);
                    } 
                }
                System.out.println("Inserted into searchLog");
            }catch(Exception e){
                System.out.println("Cannot insert into searchLog");
            }
        }
        
        try{
            jaxbContext = JAXBContext.newInstance(MovieXML.class);
        
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(movie, sw);

            return (sw.toString());

        } catch (Exception ex) {
            Logger.getLogger(SearchByTitleResource.class.getName()).log(Level.SEVERE, null, ex);
            return("error happened");
        }
        
        
//        
//        
//        
//        // movie does not exist or wrong query
//        if(movie == null){
//            //String s = "error";
//            movie = new MovieXML(-1,"null", "null", 0.0, "fail", "null");
//            try {
//                jaxbContext = JAXBContext.newInstance(MovieXML.class);
//
//                Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
//
//                jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//
//                StringWriter sw = new StringWriter();
//                jaxbMarshaller.marshal(movie, sw);
//
//                return (sw.toString());
//
//            } catch (JAXBException ex) {
//                Logger.getLogger(SearchByTitleResource.class.getName()).log(Level.SEVERE, null, ex);
//                return("error happened");
//            }
//        }
//        
//        
//        try {
//            //add search to searchLog microservice
//            
//            String s = "http://localhost:8080/SearchLog/webresources/searchlog/" + movie.getMovieId() + "+" + email;
//            URL url = new URL(s);
//            System.out.println("here2");
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestMethod("POST");
//
//            try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
//                String inputLine;
//                StringBuilder res = new StringBuilder();
//
//                while ((inputLine = in.readLine()) != null) {
//                    res.append(inputLine);
//                } 
//            }
//            System.out.println("Inserted into searchLog");
//            //
//            
//            jaxbContext = JAXBContext.newInstance(MovieXML.class);
//        
//            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
//
//            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//
//            StringWriter sw = new StringWriter();
//            jaxbMarshaller.marshal(movie, sw);
//
//            return (sw.toString());
//
//        } catch (Exception ex) {
//            Logger.getLogger(SearchByTitleResource.class.getName()).log(Level.SEVERE, null, ex);
//            return("error happened");
//        }
        
        
        
        
        
        
    }

    /**
     * PUT method for updating or creating an instance of GenericResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
}
