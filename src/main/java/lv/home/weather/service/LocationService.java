package lv.home.weather.service;

import lv.home.weather.config.CacheConfiguration;
import lv.home.weather.model.Location;
import lv.home.weather.model.cache.WeatherCacheItem;
import lv.home.weather.repository.WeatherCacheItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class LocationService {

    final private String RESP_TYPE = "json";
    final private String URL = "http://ip-api.com/"+RESP_TYPE+"/";

    @Autowired
    private WeatherCacheItemRepo cacheItemRepo;

    @Cacheable(CacheConfiguration.LOCATION_CACHE)
    public Location getLocation(String ip){
        String fooResourceUrl = URL + ip;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Location> response = restTemplate.getForEntity(fooResourceUrl , Location.class);
        Location responseBody = response.getBody();
        saveCacheItemToDB(ip, responseBody);
        return responseBody;
    }

    private void saveCacheItemToDB(String ip, Location responseBody) {
        WeatherCacheItem weatherCacheItem = new WeatherCacheItem();
        weatherCacheItem.setIp(ip);
        weatherCacheItem.setLat(responseBody.getLat());
        weatherCacheItem.setLon(responseBody.getLon());

        cacheItemRepo.save(weatherCacheItem);
    }
}
