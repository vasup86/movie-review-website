/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4.frontend.frontend;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lab4.frontend.business.Business;
import lab4.frontend.helper.Movie;
import lab4.frontend.helper.MoviesXML;

/**
 *
 * @author student
 */
@WebServlet(name = "Search", urlPatterns = {"/Search"})
public class Search extends HttpServlet {

    Authenticate autho;

    public Search() {
        autho = new Authenticate();
    }
    private final String authenticationCookieName = "login_token";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private Map.Entry<String, String> isAuthenticated(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String token = "";
        
        System.out.println("TOKEN IS");
        try {
            for (Cookie cookie : cookies) {
                System.out.println(cookie.getName());
                if (cookie.getName().equals(authenticationCookieName)) {
                    token = cookie.getValue();
                }
            }
        } catch (Exception e) {

        }
        if (!token.isEmpty())
           try {
            if (this.autho.verify(token).getKey()) {
                  Map.Entry entry= new  AbstractMap.SimpleEntry<String, String>
                             (token,this.autho.verify(token).getValue());
            return entry;

            } else {
                 Map.Entry entry= new  AbstractMap.SimpleEntry<String, String>("","");
            return entry;
            }
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }

        Map.Entry entry= new  AbstractMap.SimpleEntry<String, String>("","");
        return entry;

    }
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //token is empty if jwt is not verified (wrong user); check line 66
        String token = isAuthenticated(request).getKey();
        String uname = isAuthenticated(request).getValue();
        
        // check if logged in or not
        // redirect to index.html if not logged in and delete cookie
        if(token.isEmpty()){
            System.out.println("Empty");
            
            //delete cookie
            Cookie cookie = new Cookie(authenticationCookieName, "");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
            RequestDispatcher rd= request.getRequestDispatcher("index.html");
            rd.forward(request, response);
        }
        
        String email = (String)request.getParameter("email");
        //multiple word query will be seperated by a "-"
        String query = (String)request.getParameter("query").strip().replace(" ", "-");
        String type = (String)request.getParameter("type");
        System.out.println("token works");
        if(type.equals("title")){
            System.out.println("title");
            String titleService = System.getenv("searchbytitleservice");
            String microservice = "http://"+ titleService +"/SearchByTitle/webresources/searchByTitle";
            query = query + "+" + email;
            System.out.println(query);
            Movie movie= retriveByTitle(query, null, microservice);
            
            if(movie.getStatus().equals("error")){
                RequestDispatcher rd= request.getRequestDispatcher("movieNull.jsp");
                rd.forward(request, response);
            }
            request.setAttribute("email", email);
            request.setAttribute("movie", movie);
            request.setAttribute("username", email.substring(0, email.indexOf("@")));
            RequestDispatcher rd= request.getRequestDispatcher("movie.jsp");
            rd.forward(request, response);
        
        }else{
            System.out.println("genre");
            if(query.equals("")){
                query = "null";
            }
            String genreService = System.getenv("searchbygenreservice");
            String microservice = "http://" + genreService +"/SearchByGenre/webresources/searchByGenre";
            MoviesXML movies= retriveByGenre(query, null, microservice);
            if(movies.getMovies() == null){
                movies.setMovies(new ArrayList<Movie>());
            }
            System.out.println(movies.getMovies().size());
            request.setAttribute("email", email);
            request.setAttribute("movies", movies);
            request.setAttribute("username", email.substring(0, email.indexOf("@")));
            RequestDispatcher rd = request.getRequestDispatcher("genre.jsp");
            rd.forward(request, response);
            
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    private MoviesXML retriveByGenre(String query, String token, String microservice) {
        try {
            return (Business.getServices(query, token, microservice));
        }catch (IOException ex) {
            Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
            return (null);
        }

    }
    
     private Movie retriveByTitle(String query, String token, String microservice) {
        try {
            return (Business.getByTitle(query, token, microservice));
        }catch (Exception ex) {
            Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
            return (null);
        }

    }

}
