package lv.home.weather;

import lv.home.weather.controller.WeatherController;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WeatherApplicationTests {

	@Autowired
	private WeatherController weatherController;

	@Test
	void contextLoads() {
		assertThat(weatherController).isNotNull();
	}

}
