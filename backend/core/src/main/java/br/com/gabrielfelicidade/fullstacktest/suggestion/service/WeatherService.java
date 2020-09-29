package br.com.gabrielfelicidade.fullstacktest.suggestion.service;

import br.com.gabrielfelicidade.fullstacktest.suggestion.vo.WeatherResponseVO;

public interface WeatherService {

    WeatherResponseVO getTemperatureInCelsiusByLocation(final String location);

}
