package MyPackage;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class MyServlet
 */
@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public MyServlet() {
        super();

    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
				String apiKey = "f171935e0cbbe946d1cfb5c4e6cd2909";
				
				
		        String city = request.getParameter("city"); 
		        
		        String cityName = null;
		        if(city != null) {
		        cityName = URLEncoder.encode(city, "UTF-8");
		        }
		        
		        

		        
		        String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&appid=" + apiKey;

		            
		            URL url = new URL(apiUrl);
		           
		            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		            
		            connection.setRequestMethod("GET");
                        
		               
		                InputStream inputStream = connection.getInputStream();
		                
		                InputStreamReader reader = new InputStreamReader(inputStream);
		               
		                
		                
		                StringBuilder responseContent = new StringBuilder();
                         
		                Scanner scanner = new Scanner(reader);
		                
		                while (scanner.hasNext()) {
		                    responseContent.append(scanner.nextLine());
		                }
		                
		                scanner.close();
		               
		                
		               
		                
		                
		                Gson gson = new Gson();
		                JsonObject jsonObject = gson.fromJson(responseContent.toString(), JsonObject.class);
		                System.out.println(jsonObject);
		                
		                
		               
		                long dateTimestamp = jsonObject.get("dt").getAsLong() * 1000;
		                String date = new Date(dateTimestamp).toString();
		                
		                
		                double temperatureKelvin = jsonObject.getAsJsonObject("main").get("temp").getAsDouble();
		                int temperatureCelsius = (int) (temperatureKelvin - 273.15);
		               
		                
		                int humidity = jsonObject.getAsJsonObject("main").get("humidity").getAsInt();
		                
		                
		                double windSpeed = jsonObject.getAsJsonObject("wind").get("speed").getAsDouble();
		                
		                
		                String weatherCondition = jsonObject.getAsJsonArray("weather").get(0).getAsJsonObject().get("main").getAsString();
		                
		                
		                request.setAttribute("date", date);
		                request.setAttribute("city", city);
		                request.setAttribute("temperature", temperatureCelsius);
		                request.setAttribute("weatherCondition", weatherCondition); 
		                request.setAttribute("humidity", humidity);    
		                request.setAttribute("windSpeed", windSpeed);
		                request.setAttribute("weatherData", responseContent.toString());
		                
		                connection.disconnect();
		                
		                
		                request.getRequestDispatcher("index.jsp").forward(request, response);
		                
	}

}
