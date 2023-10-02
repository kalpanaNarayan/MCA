import React from 'react';
import { BrowserRouter as Router, Route } from 'react-router-dom';
import { Routes, Route as Route6 } from 'react-router';
import MovieList from './components/MovieList';
import MovieDetail from './components/MovieDetails';
import PopularMovies from './components/PopularMovies';
import './App.css'

function App() {
  return (
    <Router>
      <div>
        <Routes>
          <Route6 path="/" element={<MovieList />} />
          <Route6 path="/movie/:id" element={<MovieDetail />} />
        </Routes>

      </div>
    </Router>
  );
}

export default App;
