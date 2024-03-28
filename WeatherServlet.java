package L2;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Servlet implementation class WeatherServlet
 */
@WebServlet("/WeatherServlet")
public class WeatherServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String city = request.getParameter("city");
		
		PrintWriter out=response.getWriter(); 
		
		try {
			
			String weatherInfo=getWeatherInfo(city);
			JsonObject weatherData=parseWeatherInfo(weatherInfo);
			
			out.println("<html>");
			out.println("<head><title>Weather Information</title>");
			out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css'>");
			out.println("</head>");
			out.println("<body style='text-align:center;background-color:#F7CAC9'>");
			
			out.println("<h1 style='color:green'><marquee direction='left'>Weather Information for "+city+"</marquee></h1>");
			out.println("<div><b>Location:</b>"+weatherData.get("location")+"</div>");
			out.println("<div><b>Time:</b>"+weatherData.get("time")+"</div>");
			out.println("<div><b>Region:</b>"+weatherData.get("region")+"</div>");
			out.println("<div><b>Temperature:</b>"+weatherData.get("temperature")+"</div>");
			out.println("<div><b>Weather Type:</b>"+weatherData.get("type")+"</div>");
			
			out.println("</body>");
			out.println("</html>");
			
			
		}catch(Exception e) {
			out.println("<html>");
			out.println("<head><title>Error</title></head>");
			out.println("<h1 style='color:Orange'>Error retrieving weather information </h1>");
			out.println("<p>"+e.getMessage()+"</p>");
			//out.println("</body>");
			out.println("</html>");
		}
		finally {
			out.close();
		}
		
	}
	
	private String getWeatherInfo(String city) throws IOException,InterruptedException{
		
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://weatherapi-com.p.rapidapi.com/current.json?q="+city))
				.header("X-RapidAPI-Key", "0167f7afc0mshc958e9f91775833p14edf7jsn19769273470b")
				.header("X-RapidAPI-Host", "weatherapi-com.p.rapidapi.com")
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		
		return response.body();
	}
	
	private JsonObject parseWeatherInfo(String weatherInfo) {
		
		JsonParser parser=new JsonParser();
		JsonObject json=parser.parse(weatherInfo).getAsJsonObject();
		
		JsonObject weatherData=new JsonObject();
		
		JsonObject locationData=json.getAsJsonObject("location");
		
		JsonObject currentData=json.getAsJsonObject("current");
		
		weatherData.addProperty("location",locationData.getAsJsonPrimitive("name").getAsString());
		weatherData.addProperty("time",currentData.getAsJsonPrimitive("last_updated").getAsString());
		weatherData.addProperty("region",locationData.getAsJsonPrimitive("region").getAsString());
		weatherData.addProperty("temperature",currentData.getAsJsonPrimitive("temp_c").getAsDouble());
		weatherData.addProperty("type",currentData.getAsJsonObject("condition").getAsJsonPrimitive("text").getAsString());
		
		return weatherData;
	}
}