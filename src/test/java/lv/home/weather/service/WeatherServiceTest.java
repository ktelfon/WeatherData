package lv.home.weather.service;

import lv.home.weather.model.cache.WeatherCacheItem;
import lv.home.weather.model.weather.Coord;
import lv.home.weather.model.weather.WeatherData;
import lv.home.weather.repository.WeatherCacheItemRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations="classpath:application.properties")
class WeatherServiceTest {

    @Autowired
    private WeatherService weatherService;

    @MockBean
    private WeatherCacheItemRepo mockRepository;

    @BeforeEach
    void setUp() {
        when(mockRepository.findByLatAndLon(anyDouble(),anyDouble())).thenReturn(new WeatherCacheItem());
    }

    @Test
    void getWeatherDataFromLondon() throws Exception {
        Coord coord = new Coord();
        coord.setLat(51.509865);
        coord.setLon(-0.118092);
        WeatherData result = weatherService.getWeatherDataByLatLong(coord);
        assertEquals("stations",result.getBase());

    }

    @Test
    void getWeatherDataErrorOnCacheItemSsave(){
        when(mockRepository.findByLatAndLon(anyDouble(),anyDouble())).thenReturn(null);
        assertThrows(Exception.class, () -> {
            weatherService.getWeatherDataByLatLong(null);
        });
    }

    @Test
    void getWeatherDataError(){
        assertThrows(Exception.class, () -> {
            weatherService.getWeatherDataByLatLong(null);
        });
    }
}