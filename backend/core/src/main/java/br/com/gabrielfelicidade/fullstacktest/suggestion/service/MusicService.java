package br.com.gabrielfelicidade.fullstacktest.suggestion.service;

import br.com.gabrielfelicidade.fullstacktest.suggestion.vo.SpotifyQueryResponseVO;

public interface MusicService {

    SpotifyQueryResponseVO getPlaylistSuggestionByDegrees(final Double degrees);

}
