<%@ page import="com.example.B_SpringMVC1.model.Movie" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/css/movies.css">
</head>
<body>
    <%
        List<Movie> movieList = (List<Movie>) request.getAttribute("movieList");
        for (Movie movie : movieList) {
    %>
    <div class="movie-card">
        <img src="<%=movie.getImage()%>>" alt="Movie Image">
        <h2><%= movie.getMovie() %></h2>
        <p>Rating: <%= movie.getRating() %></p>
        <a href="<%= movie.getImdb_url() %>">IMDb</a>
    </div>
    <%
        }
    %>
</body>
</html>
