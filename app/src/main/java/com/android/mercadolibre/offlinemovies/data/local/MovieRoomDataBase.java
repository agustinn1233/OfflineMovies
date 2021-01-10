package com.android.mercadolibre.offlinemovies.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.android.mercadolibre.offlinemovies.data.local.dao.MovieDao;
import com.android.mercadolibre.offlinemovies.data.local.entitiy.MovieEntity;

@Database(entities = {MovieEntity.class}, version = 1, exportSchema = false) // Indica base de datos, indica exportar el esquema y nos puede indicar errores.
public abstract class MovieRoomDataBase extends RoomDatabase {
    // Al ser abstract no vamos a implementar metodos obligatorios.
    // Lo unico que incluye es la definicion de el obj Dao.

    public abstract MovieDao getMovieDao();

}
