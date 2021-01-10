package com.android.mercadolibre.offlinemovies.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.android.mercadolibre.offlinemovies.data.local.entitiy.MovieEntity;

import java.util.List;

@Dao
public interface MovieDao {
    // Se definen las operaciones que se van a llevar acabo con la entidad peliculas (movies)

    @Query("SELECT * FROM movies")
    LiveData<List<MovieEntity>> loadMovies();

    // Si hay conflictos con alguna pelicula que ya se encuentra insertada, esto la remplaza.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveMovies(List<MovieEntity> movieEntities);
}
