package lv.home.weather.service;

import lv.home.weather.config.CacheConfiguration;
import lv.home.weather.model.cache.WeatherCacheItem;
import lv.home.weather.model.weather.Coord;
import lv.home.weather.model.weather.WeatherData;
import lv.home.weather.repository.WeatherCacheItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Value("${weather.api.key}")
    private String key;
    final private String URL = "https://api.openweathermap.org/data/2.5/weather";

    @Autowired
    private WeatherCacheItemRepo cacheItemRepo;

    @Cacheable(CacheConfiguration.WEATHER_CACHE)
    public WeatherData getWeatherDataByLatLong(Coord coord) throws Exception {
        String url = URL + "?lat=" + coord.getLat() + "&lon=" + coord.getLon() + "&appid=" + key;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<WeatherData> response = restTemplate.getForEntity(url, WeatherData.class);
        WeatherData responseBody = response.getBody();
        updateCacheItem(coord, responseBody.getMain().getTemp());
        return responseBody;
    }

    private WeatherCacheItem updateCacheItem(Coord coord, double temp) {
        WeatherCacheItem item = cacheItemRepo.findByLatAndLon(coord.getLat(), coord.getLon());
        item.setTemp(temp);
        cacheItemRepo.save(item);
        return item;
    }
}
