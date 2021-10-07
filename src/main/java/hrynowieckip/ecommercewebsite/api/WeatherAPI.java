package hrynowieckip.ecommercewebsite.api;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class WeatherAPI {
    public String getWeatherForCity(String city) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://community-open-weather-map.p.rapidapi.com/weather?q=" + city + "&lat=0&lon=0&callback=test&id=2172797&lang=null&units=%22metric%22%20or%20%22imperial%22&mode=xml%2C%20html")
                .get()
                .addHeader("x-rapidapi-key", "3e0feb1594msh49fd558a8c1b3e1p1b4746jsn4dc710587d4d")
                .addHeader("x-rapidapi-host", "community-open-weather-map.p.rapidapi.com")
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (response.code() == 200) return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
