<%-- 
    Document   : main
    Created on : March 8, 2023, 9:56:26 PM
    Author     : student
--%>

<%@page import="lab4.frontend.helper.MoviesXML"%>
<%@page import="lab4.frontend.helper.Movie"%>
<%@page import="java.util.Base64"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        <link rel="stylesheet" href="https://unpkg.com/bootstrap-table@1.21.2/dist/bootstrap-table.min.css"> 
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        
    </head>
    <body>
        <style>
            body{
                height: 100vh;
            }
            .container{
                border-radius: 50px;
                background: #fcfcfc;
                box-shadow:  20px 20px 60px #d6d6d6,
                             -20px -20px 60px #ffffff;
                            
                height:80%;
                overflow-y: scroll;
                padding: 1%!important;
            }
            
            img{
                width:80px;
                height: 120px;
            }
            
            .form{
                display:flex;
                flex-direction: column;
            }
                     
            .form .forms{
                display:flex;
                flex-direction: row;
            }
        </style>
        
        <%
            MoviesXML movies = (MoviesXML) request.getAttribute("movies");
            String email = (String)request.getAttribute("email");
        %>
        
        
         <h1>Hello <%=request.getAttribute("username")%>!</h1>
        
        <div class = "container">
            <form action= "Search"  class="forms" method="post" >
                      <!--   id="genreInput" onkeyup="searchGenre()" -->
                      <input type="text" name="query" placeholder="Search Genre">
                      <input type="hidden" name="email" value="<%=email%>">
                      <input name="type" value="genre" type="hidden">
                      <button
                        class='form-control'
                        type="submit"
                        value="Go"
                      >Go </button>
                </form>
            <table class="table table-bordered" id="table" data-url="json/data1.json"  data-filter-control="true" data-show-search-clear-button="true">
                <thead>
                <tr>
                  <th data-field="id">Poster</th>
                  <th data-field="id">Movie</th>
                  <th data-field="name" data-filter-control="input">Genre</th>
                  <th data-field="price" data-filter-control="select">Rating</th>
                </tr>
              </thead>
              <tbody id="myTable">
<!--                <tr>
                  <td >Movie title</td>
                  <td >Rating</td>
                </tr>
                <tr>
                  <td >Movie title</td>
                  <td >Rating</td>
                </tr>
                <tr>
                  <td >Movie title</td>
                  <td >Rating</td>
                </tr>-->
                
                <%for (Movie movie: movies.getMovies()){%>
                <tr>
                    <td>
                        <img src="data:image/png;base64,<%=movie.getFilmPoster()%>" />
                    </td>
                    <td>
                        <%=movie.getTitle()%>
                    </td>
                    <td>
                        <%=movie.getGenre()%>
                    </td>
                    <td>
                        <%=movie.getRating()%>
                    </td>
                </tr>
                
                <%}%>
                  
              </tbody>
                
            </table>
                
            
        </div>
                
        <div>

        </div>      
        
        <script src="https://unpkg.com/bootstrap-table@1.21.2/dist/bootstrap-table.min.js"></script>
        <script src="https://unpkg.com/bootstrap-table@1.21.2/dist/extensions/filter-control/bootstrap-table-filter-control.min.js"></script>
        
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    </body>
</html>
