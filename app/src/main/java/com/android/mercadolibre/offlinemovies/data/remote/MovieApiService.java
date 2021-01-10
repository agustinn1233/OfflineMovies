package com.android.mercadolibre.offlinemovies.data.remote;

import com.android.mercadolibre.offlinemovies.data.remote.model.MoviesResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieApiService {
    // Metodos de acceso a la API.
    // Cargar peliculas populares
    @GET("movie/popular")
    Call<MoviesResponse> loadPopularMovies();

}
