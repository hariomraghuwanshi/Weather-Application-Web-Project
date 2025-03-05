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
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//API Key
				String apiKey = "f171935e0cbbe946d1cfb5c4e6cd2909";
				
				// Get the city from the form input
				//retrieve the city name enter by the user in an HTML form
		        String city = request.getParameter("city"); 
		        
		        String cityName = null;
		        if(city != null) {
		        cityName = URLEncoder.encode(city, "UTF-8");
		        }
		        
		        

		        // Create the URL for the OpenWeatherMap API request
		        //Constructing the API URL
		        String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&appid=" + apiKey;

		            
		            URL url = new URL(apiUrl);
		            //HttpURLConnection Opens a connection to the API
		            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		            //setRequestMethod("GET") specifies that this is read-only request(fetching data).
		            connection.setRequestMethod("GET");
                        
		               //InputStream represents the raw data stream from the API(bytes)
		                InputStream inputStream = connection.getInputStream();
		                //InputStreamReader converts the byte stream into a character stream(text)
		                InputStreamReader reader = new InputStreamReader(inputStream);
		               // System.out.println(reader);
		                
		                //want to store in string
		                StringBuilder responseContent = new StringBuilder();
                         
		                Scanner scanner = new Scanner(reader);
		                
		                while (scanner.hasNext()) {
		                    responseContent.append(scanner.nextLine());
		                }
		                
		                scanner.close();
		                //System.out.println(responseContent);
		                
		                //Typecasting = parsing data into JSON
		                
		                // Parse the JSON response to extract temperature, date, and humidity
		                Gson gson = new Gson();
		                JsonObject jsonObject = gson.fromJson(responseContent.toString(), JsonObject.class);
		                System.out.println(jsonObject);
		                
		                
		                //Date & Time
		                long dateTimestamp = jsonObject.get("dt").getAsLong() * 1000;
		                String date = new Date(dateTimestamp).toString();
		                
		                //Temperature
		                double temperatureKelvin = jsonObject.getAsJsonObject("main").get("temp").getAsDouble();
		                int temperatureCelsius = (int) (temperatureKelvin - 273.15);
		               
		                //Humidity
		                int humidity = jsonObject.getAsJsonObject("main").get("humidity").getAsInt();
		                
		                //Wind Speed
		                double windSpeed = jsonObject.getAsJsonObject("wind").get("speed").getAsDouble();
		                
		                //Weather Condition
		                String weatherCondition = jsonObject.getAsJsonArray("weather").get(0).getAsJsonObject().get("main").getAsString();
		                
		                // Set the data as request attributes (for sending to the jsp page)
		                request.setAttribute("date", date);
		                request.setAttribute("city", city);
		                request.setAttribute("temperature", temperatureCelsius);
		                request.setAttribute("weatherCondition", weatherCondition); 
		                request.setAttribute("humidity", humidity);    
		                request.setAttribute("windSpeed", windSpeed);
		                request.setAttribute("weatherData", responseContent.toString());
		                
		                connection.disconnect();
		                
		                // Forward the request to the weather.jsp page for rendering
		                request.getRequestDispatcher("index.jsp").forward(request, response);
		                
	}

}
