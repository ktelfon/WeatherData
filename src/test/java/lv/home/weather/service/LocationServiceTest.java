package lv.home.weather.service;

import lv.home.weather.model.Location;
import lv.home.weather.repository.WeatherCacheItemRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(LocationService.class)
class LocationServiceTest {

    @Autowired
    private LocationService locationService;

    @MockBean
    private WeatherCacheItemRepo mockRepository;

    @Test
    void getLocationLondon() {
//        when(mockRepository.save(any())).thenReturn();
        Location location = locationService.getLocation("185.86.151.11");
        assertEquals("London",location.getCity());
    }

    @Test
    void getLocationError() {
        Location location = locationService.getLocation("185.86sdsd.151.11");
        assertEquals("fail",location.getStatus());
    }
}