package br.com.gabrielfelicidade.fullstacktest.suggestion.service.impl;

import br.com.gabrielfelicidade.fullstacktest.suggestion.service.MusicService;
import br.com.gabrielfelicidade.fullstacktest.suggestion.vo.SpotifyQueryResponseVO;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Base64;

@Service
public class SpotifyServiceImpl implements MusicService {

    @Value("${app.integrations.spotify.auth}")
    private String spotifyAuthUrl;

    @Value("${app.integrations.spotify.search}")
    private String spotifySearchUrl;

    @Value("${app.credentials.spotify.id}")
    private String spotifyClientId;

    @Value("${app.credentials.spotify.secret}")
    private String spotifyClientSecret;

    @Override
    public SpotifyQueryResponseVO getPlaylistSuggestionByDegrees(final Double degrees) {
        return WebClient
                .create()
                .get()
                .uri(String.format(spotifySearchUrl, getSuggestedGenreByDegrees(degrees)))
                .header("Authorization", String.format("Bearer %s", getAuthorizationToken()))
                .retrieve()
                .bodyToMono(SpotifyQueryResponseVO.class)
                .block();
    }

    private String getSuggestedGenreByDegrees(final Double degrees) {
        if (degrees < 10) return "classical";
        else if (degrees >= 10 && degrees <= 14) return "rock";
        else if (degrees >= 15 && degrees <= 30) return "pop";
        else return "party";
    }

    private String getAuthorizationToken() {
        return WebClient
                .create()
                .post()
                .uri(spotifyAuthUrl)
                .bodyValue("grant_type=client_credentials")
                .header("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .header("Authorization", String.format("Basic %s", Base64.getEncoder().encodeToString(String.format("%s:%s", spotifyClientId, spotifyClientSecret).getBytes())))
                .retrieve()
                .bodyToMono(JsonNode.class)
                .block()
                .get("access_token")
                .asText();
    }

}
