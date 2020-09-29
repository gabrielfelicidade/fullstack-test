package br.com.gabrielfelicidade.fullstacktest.suggestion.service.impl;

import br.com.gabrielfelicidade.fullstacktest.suggestion.service.WeatherService;
import br.com.gabrielfelicidade.fullstacktest.suggestion.vo.WeatherResponseVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class OpenWeatherMapServiceImpl implements WeatherService {

    @Value("${app.credentials.openweather.api-key}")
    private String openWeatherApiKey;

    @Value("${app.integrations.openweather.current}")
    private String openWeatherUrl;

    @Override
    public WeatherResponseVO getTemperatureInCelsiusByLocation(String location) {
        return WebClient
                .create()
                .get()
                .uri(String.format(openWeatherUrl, location, openWeatherApiKey))
                .retrieve()
                .bodyToMono(WeatherResponseVO.class)
                .block();
    }

}
