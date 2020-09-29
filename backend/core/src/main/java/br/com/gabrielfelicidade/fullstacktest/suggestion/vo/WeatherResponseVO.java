package br.com.gabrielfelicidade.fullstacktest.suggestion.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class WeatherResponseVO {

    private Double temperature;

    @JsonProperty("main")
    private void unpackMain(Map<String, String> main) {
        this.temperature = Double.valueOf(main.get("temp"));
    }

}
