/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4.frontend.business;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import lab4.frontend.helper.MoviesXML;
import lab4.frontend.helper.Movie;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author student
 */
public class Business {

    public static boolean isAuthenticated(String username, String password) {
        return true;
    }

    public static MoviesXML getServices(String query, String token, String microservice) throws IOException {
        Client searchclient = ClientBuilder.newClient();
        WebTarget searchwebTarget = searchclient.target(microservice);
        InputStream is = searchwebTarget.path(query).request(MediaType.APPLICATION_XML).get(InputStream.class);
        String xml = IOUtils.toString(is, "utf-8");
        MoviesXML movies = movieXMLToObject(xml);
        return (movies);

    }

    private static MoviesXML movieXMLToObject(String xml) {
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(MoviesXML.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            MoviesXML movies = (MoviesXML) jaxbUnmarshaller.unmarshal(new StringReader(xml));
            return movies;

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
    
   public static Movie getByTitle(String query, String token, String microservice) throws IOException {
        Client searchclient = ClientBuilder.newClient();
        WebTarget searchwebTarget = searchclient.target(microservice);
        InputStream is = searchwebTarget.path(query).request(MediaType.APPLICATION_XML).get(InputStream.class);
        String xml = IOUtils.toString(is, "utf-8");
        Movie movies = movieToObject(xml);
        return (movies);
    }
   
   private static Movie movieToObject(String xml) {
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(Movie.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            Movie movie = (Movie) jaxbUnmarshaller.unmarshal(new StringReader(xml));
            return movie;

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
   

}
