package com.android.mercadolibre.offlinemovies.data.remote.model;

import com.android.mercadolibre.offlinemovies.data.local.entitiy.MovieEntity;

import java.util.List;

public class MoviesResponse {
    private List<MovieEntity> results;

    public List<MovieEntity> getResults() {
        return results;
    }

    public void setResults(List<MovieEntity> results) {
        this.results = results;
    }
}
