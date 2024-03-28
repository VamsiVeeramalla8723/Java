<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Weather App</title>
    <style>
    	h1{
    		color:red;
    		text-align:center;
    	}
    	input[type=submit]{
    		background-color:red;
    		color:white;
    		padding:10px 30px;
    		margin:8px 0px;
    		border:none;
    		border-radius:5px;
    	}
    	input[type=text]{
    		background-color:white;
    		padding:10px 30px;
    		border:1px solid #ccc;
    		box-sizing:border-box;
    	}
    	div{
    		border-radius:5px;
    		padding:20px;
    		background-color:cyan;
    	}
    </style>
</head>
<body style=background-color:#EFC050;text-align:center>
    <h1>Weather App</h1>
    <div>
	    <form action="WeatherServlet" method="get"><br>
	        <b style=color:green>Enter City: </b><input type="text" name="city" placeholder="Enter a Location...">
	        <input type="submit" value="Get Weather">
	    </form>
    </div>
</body>
</html>