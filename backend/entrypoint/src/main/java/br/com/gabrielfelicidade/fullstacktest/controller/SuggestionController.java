package br.com.gabrielfelicidade.fullstacktest.controller;

import br.com.gabrielfelicidade.fullstacktest.suggestion.service.MusicService;
import br.com.gabrielfelicidade.fullstacktest.suggestion.service.WeatherService;
import br.com.gabrielfelicidade.fullstacktest.suggestion.vo.SpotifyQueryResponseVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("suggestions")
@RequiredArgsConstructor
@Slf4j
public class SuggestionController {

    private final MusicService musicService;

    private final WeatherService weatherService;

    @CrossOrigin
    @GetMapping
    public ResponseEntity<SpotifyQueryResponseVO> getSuggestionsByLocation(@RequestParam String location) {
        try {
            log.info("Get suggestion request: location={}", location);

            return ResponseEntity.ok(
                    musicService.getPlaylistSuggestionByDegrees(
                            weatherService.getTemperatureInCelsiusByLocation(location).getTemperature()
                    )
            );
        } catch (Exception exception) {
            log.error("Exception when getting suggestions by location: location={}, error={}", location, exception.getMessage());

            return ResponseEntity.badRequest().build();
        }
    }

}
