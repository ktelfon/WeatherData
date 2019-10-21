package lv.home.weather.repository;

import lv.home.weather.model.cache.WeatherCacheItem;
import org.springframework.data.repository.CrudRepository;

public interface WeatherCacheItemRepo extends CrudRepository<WeatherCacheItem, Long> {
    WeatherCacheItem findByLatAndLon(double lat, double lon);
}
