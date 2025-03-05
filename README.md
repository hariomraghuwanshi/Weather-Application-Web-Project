
# Weather App

A real-time weather application that allows users to search for weather information by city. The app retrieves data such as temperature, humidity, air pressure, wind speed, and weather conditions from the OpenWeatherMap API and displays it on a user-friendly interface.

## Features:
- Search for weather information by city name.
- Displays real-time weather data such as temperature (in Celsius), humidity, wind speed, and weather condition.
- Retrieves weather data securely from the OpenWeatherMap API using a backend servlet.
- Data is parsed from JSON format using the Gson library.

## Technologies Used:
- **Frontend:** HTML, CSS, JavaScript
- **Backend:** Java, JSP (Java Server Pages), Servlet
- **Weather API:** OpenWeatherMap API
- **JSON Parsing:** Gson library
- **Security:** Data fetched securely using HTTP GET requests
- **IDE:** Eclipse
- **Server:** Apache Tomcat 10.1

## Setup Instructions:
1. Clone this repository:
    ```bash
    git clone https://github.com/your-username/weather-app.git
    ```

2. Navigate to the project directory:
    ```bash
    cd weather-app
    ```

3. Create an account on [OpenWeatherMap](https://openweathermap.org/) and get your API key.

4. Add the **Gson library** to your project:
    - Download the Gson `.jar` file from [Gson GitHub page](https://github.com/google/gson).
    - Include it in your `lib` folder (if not already included).

5. Configure the servlet and weather API integration:
    - Edit the servlet to include your API key:  
    `String apiKey = "YOUR_API_KEY";`
    - Update the form action to point to `/MyServlet`.

6. Run the application on **Apache Tomcat 10.1** in **Eclipse**.

## How It Works:
- When a user enters a city name, the app sends a **POST request** to the servlet.
- The servlet constructs an API URL with the city name and makes a request to the OpenWeatherMap API.
- The JSON response is parsed using **Gson** and the relevant weather data is extracted.
- The data (date, temperature, humidity, wind speed, etc.) is forwarded to a JSP page (`index.jsp`) for display.

## Example Screenshot:
![Weather App Screenshot](images/weather-screenshot.png)

## Contributing:
Feel free to fork the repository and submit pull requests if you would like to contribute to the development of this project!
