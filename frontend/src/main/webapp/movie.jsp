<%-- 
    Document   : movie
    Created on : Mar 12, 2023, 2:40:42 PM
    Author     : student
--%>

<%@page import="lab4.frontend.helper.Movie"%>
<%@page import="java.util.Base64"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
            Movie movie = (Movie)request.getAttribute("movie");
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=movie.getTitle()%></title>
    </head>
    <body>
        <style>
            .container {
            -ms-overflow-style: none;  /* IE and Edge */
            scrollbar-width: none;  /* Firefox */
          }

            body{
                height: 100vh;
                display: flex;
                justify-content: center;
                align-items: center;
                margin: 0;
               
            }
            .container{
                border-radius: 50px;
                background: #fcfcfc;
                box-shadow:  20px 20px 60px #d6d6d6,
                             -20px -20px 60px #ffffff;
                            
                width: 95%;
                height:91%;
                overflow-y: scroll;
                padding: 1%!important;
                
            }
            .desc{
                display:flex;
                flex-direction: row;
                width: 100%;
                height:50%;
                justify-content: space-around;
            }
            .desc:nth-child(2){
                height: 100%;
            }
            img{
                height:100%;
            }
            
            .poster{
                display:flex;
                flex-direction: row;
                width:20%;
            }
           
            .comments{
                padding-top:25px;
                display:flex;
                flex-direction: column;
                width:80%;
                height:50%
            }
            .comment{
                padding: 3px 0 3px 0;
                display:flex;
                flex-direction: row;
                justify-content: left;
                width: 100%;
                height: 15%;
            }
            .comment .userImg{
                display: flex;
                justify-content: right;
                padding-right:3px;
                width:10%;
                height:100%;
                    
            }
            .comment .com{
                display:flex;
                flex-direction: column;
                justify-content: center;
            }
            .rating{
                display:flex;
                flex-direction: row;
            }
            .star{
                width:20%;
                height:15%;
            }
            
            .title{
                padding-left: 10px;
            }
            
        </style>
        <div class="container">
            <div class="desc">
                <div class="poster">
                     <img src="data:image/png;base64,<%=movie.getFilmPoster()%>" />
                     
                     <div class="title">
                        <h1><%=movie.getTitle()%></h1>

                        <h2><%=movie.getGenre()%></h2>
                    </div>
                </div>
                <div class="rating">
                    <img class = "star" src="res/star.png"/>
                    <h2><%=movie.getRating()%></h2>
                    
                </div>
            </div>
                
            <div class = "comments">
                <div class="comment">
                    <div class="userImg">
                        <img src="res/userIcon.png"/>
                    </div>
                    <div class="com">
                        <div class = "user">Adam</div>
                        <div class = "co">Great Movie!!</div>
                    </div>
                </div> 
                <div class="comment">
                    <div class="userImg">
                        <img src="res/userIcon.png"/>
                    </div>
                    <div class="com">
                        <div class = "user">Adam</div>
                        <div class = "co">5/5</div>
                    </div>
                </div> 
                <div class="comment">
                    <div class="userImg">
                        <img src="res/userIcon.png"/>
                    </div>
                    <div class="com">
                        <div class = "user">John</div>
                        <div class = "co">Amazing story!!</div>
                    </div>
                </div> 
                <div class="comment">
                    <div class="userImg">
                        <img src="res/userIcon.png"/>
                    </div>
                    <div class="com">
                        <div class = "user">user156548</div>
                        <div class = "co">boring :(</div>
                    </div>
                </div> 
            </div>
        </div>
    </body>
</html>
