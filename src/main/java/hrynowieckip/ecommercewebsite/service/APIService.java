package hrynowieckip.ecommercewebsite.service;

import hrynowieckip.ecommercewebsite.api.WeatherAPI;
import hrynowieckip.ecommercewebsite.domain.model.User;
import hrynowieckip.ecommercewebsite.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class APIService {
    private final UserRepository userRepository;
    private final WeatherAPI weatherAPI;

    public String getWeatherForCity() {
        log.debug("Setting the default city as \"Warszawa\"");
        String userCity = "Warszawa";
        String currentUserUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        log.debug("Search for a user with a username: {}", currentUserUsername);
        Optional<User> userOptional = userRepository.findByUsername(currentUserUsername);
        if (userOptional.isPresent()) {
            userCity = userOptional.get().getUserDetails().getCity();
            if (userCity.isEmpty()) userCity = "Warszawa";
        }
        log.debug("Getting weather for city: {}", userCity);
        String weatherForCity = weatherAPI.getWeatherForCity(userCity);
        if (weatherForCity.isEmpty()) {
            log.debug("Something went wrong with the weather api for city: {}", userCity);
            return userCity + "_err";
        }

        int strStart = weatherForCity.indexOf("\"temp\":");
        int strEnd = weatherForCity.indexOf(",\"feels_like\"");

        double tempForCity = Double.parseDouble(weatherForCity.substring(strStart + 7, strEnd)) - 273.15;
        int roundedTempForCity = (int) Math.round(tempForCity);

        return userCity + " " + roundedTempForCity + "\u00B0" + "C";
    }
}
