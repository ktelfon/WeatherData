package lv.home.weather.controller;

import lv.home.weather.model.Location;
import lv.home.weather.model.weather.Coord;
import lv.home.weather.model.weather.WeatherData;
import lv.home.weather.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WeatherController.class)
class WeatherControllerTest {

    @LocalServerPort
    private String port;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LocationService locationService;
    @MockBean
    private WeatherService weatherService;

    private String testIp = "185.86.151.11";

    private Location l;
    private WeatherData w;
    private HttpHeaders headers;
    private TestRestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        w = new WeatherData();
        w.setName("stations");

        l = new Location();

        l.setLon(-0.118092);
        l.setLat(51.509865);

        headers = new HttpHeaders();
        restTemplate = new TestRestTemplate();
    }

//    @Test
//    public void testLondonIp() throws Exception {
//        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
//
//        ResponseEntity<String> response = restTemplate.exchange(
//                createURLWithPort("/weather"), HttpMethod.GET, entity, String.class);
//        String expected = "{\"id\":1,\"name\":\"Rajesh Bhojwani\",\"description\":\"Class 10\"}";
//        JSONAssert.assertEquals(expected, response.getBody(), false);
//    }

    @Test
    void testWeatherControllerOK() throws Exception {
        when(locationService.getLocation(testIp)).thenReturn(l);
        Coord coord = new Coord();
        coord.setLat(51.509865);
        coord.setLon(-0.118092);
        when(weatherService.getWeatherDataByLatLong(coord)).thenReturn(new WeatherData());

        this.mockMvc.perform(
                get("/weather").with(
                        request -> {
                            request.setRemoteAddr(testIp);
                            return request;
                        }))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void testWeatherDataServiceFail() throws Exception {

        when(locationService.getLocation(testIp)).thenReturn(null);
        when(weatherService.getWeatherDataByLatLong(any())).thenThrow(new Exception());

        this.mockMvc.perform(
                get("/weather").with(
                        request -> {
                            request.setRemoteAddr(testIp);
                            return request;
                        }))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void testLocationServiceFail() throws Exception {

        l.setStatus("fail");
        when(locationService.getLocation(any())).thenReturn(l);

        this.mockMvc.perform(
                get("/weather").with(
                        request -> {
                            request.setRemoteAddr(testIp);
                            return request;
                        }))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}