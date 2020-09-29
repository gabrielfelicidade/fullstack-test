import React, { useState } from 'react';
import './App.css';

const App = () => {
  const [state, setState] = useState({
    location: '',
    spotifyDescription: '',
    spotifyUrl: '',
    error: ''
  });

  const handleChangeLocation = (event) => {
    setState({ ...state, location: event.target.value });
  }

  const getSpotifyPlaylistByLocation = () => {
    const apiUrl = `http://localhost:8080/suggestions/?location=${state.location}`;

    fetch(apiUrl)
      .then((res) => res.json())
      .then((repos) => setState({ ...state, spotifyDescription: repos.description, spotifyUrl: repos.spotifyUrl, error: '' }))
      .catch((err) => setState({ ...state, error: err }));
  }

  return (
    <div className="App">
      <div>
        <h1>Sugestões de Playlists</h1>
      </div>
      <div className="form">
        <label>Localização:</label>
        <input type="text" value={state.location} onChange={handleChangeLocation} />
        <button onClick={getSpotifyPlaylistByLocation}>Obter sugestão</button>
      </div>
      {state.spotifyUrl && !state.error ? (
        <div>
          <a href={state.spotifyUrl} target="_blank" rel="noopener noreferrer">{state.spotifyDescription}</a>
        </div>
      ) : null}
      {state.error ? (
        <div>
          <p>Não foi possível recomendar baseado na cidade informada. Por favor, informar outra localização.</p>
        </div>
      ) : null}
    </div>
  );
}

export default App;
