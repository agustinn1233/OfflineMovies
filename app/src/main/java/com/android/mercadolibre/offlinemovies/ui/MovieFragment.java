package com.android.mercadolibre.offlinemovies.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.mercadolibre.offlinemovies.R;
import com.android.mercadolibre.offlinemovies.data.local.entitiy.MovieEntity;
import com.android.mercadolibre.offlinemovies.data.network.Resource;
import com.android.mercadolibre.offlinemovies.viewmodel.MovieViewModel;

import java.util.List;


public class MovieFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    List<MovieEntity> movieList;
    MyMovieRecyclerViewAdapter adapter;
    MovieViewModel movieViewModel;

    public MovieFragment() {
    }

    @SuppressWarnings("unused")
    public static MovieFragment newInstance(int columnCount) {
        MovieFragment fragment = new MovieFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }

        // Se instancia el movieViewModel.
        movieViewModel = new ViewModelProvider(requireActivity(), new ViewModelProvider.NewInstanceFactory()).get(MovieViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            adapter = new MyMovieRecyclerViewAdapter(
                getActivity(), // Nos da el contexto en un fragment.
                    movieList // Cargo la lista de movies.
            );
            recyclerView.setAdapter(adapter);
            loadMovies();

        }
        return view;
    }

    private void loadMovies() {
        // A travez del viewModel de peliculas, se invoca el metodo getPopularMovies
        movieViewModel.getPopularMovies().observe(requireActivity(), new Observer<Resource<List<MovieEntity>>>() {
            @Override
            public void onChanged(Resource<List<MovieEntity>> listResource) {
                // Se lanza cuando se recibe la lista de peliculas populares
                // guardamos la lista.
                movieList = listResource.data;
                // Se refresca el adapter, para la lista de peliculas.
                adapter.setData(movieList);
            }
        });
    }
}