package br.com.gabrielfelicidade.fullstacktest.suggestion.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class SpotifyQueryResponseVO {

    private String description;
    private String spotifyUrl;

    @JsonProperty("playlists")
    private void unpackPlaylists(Map<String, Object> playlists) {
        final var items = (List<Map<String, Object>>) playlists.get("items");

        if(items.isEmpty()) return;

        this.description = (String) items.get(0).get("description");
        this.spotifyUrl = (String) ((Map<String, Object>) items.get(0).get("external_urls")).get("spotify");
    }

}
