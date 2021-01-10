package com.android.mercadolibre.offlinemovies.data;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.android.mercadolibre.offlinemovies.app.MyApp;
import com.android.mercadolibre.offlinemovies.data.local.MovieRoomDataBase;
import com.android.mercadolibre.offlinemovies.data.local.dao.MovieDao;
import com.android.mercadolibre.offlinemovies.data.local.entitiy.MovieEntity;
import com.android.mercadolibre.offlinemovies.data.network.NetworkBoundResource;
import com.android.mercadolibre.offlinemovies.data.network.Resource;
import com.android.mercadolibre.offlinemovies.data.remote.MovieApiService;
import com.android.mercadolibre.offlinemovies.data.remote.RequestInterceptor;
import com.android.mercadolibre.offlinemovies.data.remote.model.MoviesResponse;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.android.mercadolibre.offlinemovies.data.remote.ApiConstants.BASE_URL;

public class MovieRepository {

    private final MovieApiService movieApiService;
    private final MovieDao movieDao;

    public MovieRepository() {

        // Room DataBase. (Local)
        MovieRoomDataBase movieRoomDataBase = Room.databaseBuilder(
                MyApp.getContext(),
                MovieRoomDataBase.class,
                "db_movies"
        ).build();
        movieDao = movieRoomDataBase.getMovieDao();

        // (API EXTERNA)
        // RequestInterceptor (Objeto que intercepta informacion)
        // RequestInterceptor (Para incluir en la url de la solicitud nuestro TOKEN o API_KEY para autorizar al user a realizar la solicitud.
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(new RequestInterceptor());
        OkHttpClient client = builder.build();

        // Remote Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client) // Se le pasa el client creado.
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Inicializa la interfaz de MovieApiServices.
        movieApiService = retrofit.create(MovieApiService.class);

    }

    // Obtener listado de peliculas populares, diferencia cuando se accede desde la api externa (por que tenemos conexion a internet) o local desde la bd (cuando no tenemos internet)

    public LiveData<Resource<List<MovieEntity>>> getPopularMovies() {
        // Tipo que devuelve room, tipo que devuelve la api
        return new NetworkBoundResource<List<MovieEntity>, MoviesResponse>() {

            @Override
            protected void saveCallResult(@NonNull MoviesResponse item) {
                // Guarda la respuesta del servidor en la base de datos local (por si quedamos sin internet nos en algun momento)
                movieDao.saveMovies(item.getResults());
            }

            @NonNull
            @Override
            protected LiveData<List<MovieEntity>> loadFromDb() {
                // Metodo que devuelve los datos que dispongamos en room (BD LOCAL)
                return movieDao.loadMovies();
            }

            @NonNull
            @Override
            protected Call<MoviesResponse> createCall() {
                // Realiza la llamada a la api externa (en caso de tener conexion a internet)
                return movieApiService.loadPopularMovies();
            }
        }.getAsLiveData();
    }

}
