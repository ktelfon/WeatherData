package lv.home.weather.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfiguration {

    public final static String LOCATION_CACHE = "locations";
    public final static String WEATHER_CACHE = "weatherData";
    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager(WEATHER_CACHE,LOCATION_CACHE);
    }
}
