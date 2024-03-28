<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Speed Calculator</title>
</head>
<body style="background-color:black">
	<h1 style=color:red;text-align:center>Speed Calculator</h1>
    <form action="SpeedCalculatorServlet" method="post" style=text-align:center>
        <label for="distance" style="color:green"><b>Distance:(Kms)</b></label><br>
        <input type="number" id="distance" name="distance" value="km" required><br><br>

        <label for="time" style="color:green"><b>Time:(hrs)</b></label><br>
        <input type="number" id="time" name="time" value="hr" required><br><br>

        <input type="submit" value="Calculate">&emsp;&emsp;&emsp;<input type="reset" value="Clear">
    </form>
</body>
</html>