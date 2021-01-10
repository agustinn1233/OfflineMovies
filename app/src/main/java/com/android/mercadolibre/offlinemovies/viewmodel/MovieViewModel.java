package com.android.mercadolibre.offlinemovies.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.android.mercadolibre.offlinemovies.data.MovieRepository;
import com.android.mercadolibre.offlinemovies.data.local.entitiy.MovieEntity;
import com.android.mercadolibre.offlinemovies.data.network.Resource;

import java.util.List;

public class MovieViewModel extends ViewModel {
    // Declaro el obj en el que almaceno las peliculas populares de nuestro repositorio.

    private final LiveData<Resource<List<MovieEntity>>> popularMovies;
    private MovieRepository movieRepository;

    // Constructor
    public MovieViewModel() {
        movieRepository = new MovieRepository();
        popularMovies = movieRepository.getPopularMovies(); // Cargo el listado de peliculas del movieRepository.
    }

    // Metodo que devuleve el listado de peliculas populares
    public LiveData<Resource<List<MovieEntity>>> getPopularMovies() {
        return popularMovies;
    }

}
