package lv.home.weather.controller;

import lv.home.weather.model.Location;
import lv.home.weather.model.weather.Coord;
import lv.home.weather.model.weather.WeatherData;
import lv.home.weather.service.LocationService;
import lv.home.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static java.util.Objects.isNull;

@RestController
public class WeatherController {

    @Value("${msg.error.location}")
    private String locationErrorMsg;

    @Value("${msg.error.weather}")
    private String weatherErrorMsg;

    private LocationService locationService;
    private WeatherService weatherService;

    @Autowired
    public WeatherController(LocationService locationService, WeatherService weatherService) {
        this.locationService = locationService;
        this.weatherService = weatherService;
    }

    @GetMapping(value = "/weather", produces = "application/json")
    public ResponseEntity<?> getWeather(HttpServletRequest request) {
        String remoteAddr = request.getRemoteAddr();
        Location location = locationService.getLocation(remoteAddr);

        //checking if getting the location failed
        if (isNull(location) || "fail".equals(location.getStatus())) {
            return new ResponseEntity<>(locationErrorMsg, HttpStatus.BAD_REQUEST);
        }

        WeatherData weatherData;
        //caching if weather data failed
        try {
            Coord coord = new Coord();
            coord.setLat(location.getLat());
            coord.setLon(location.getLon());
            weatherData = weatherService.getWeatherDataByLatLong(coord);
        } catch (Exception e) {
            return new ResponseEntity<>(weatherErrorMsg, HttpStatus.BAD_REQUEST);
        }


        return new ResponseEntity<>(weatherData, HttpStatus.OK);
    }
}
