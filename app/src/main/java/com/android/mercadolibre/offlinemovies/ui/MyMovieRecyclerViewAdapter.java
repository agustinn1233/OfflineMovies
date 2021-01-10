package com.android.mercadolibre.offlinemovies.ui;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.mercadolibre.offlinemovies.R;
import com.android.mercadolibre.offlinemovies.data.local.entitiy.MovieEntity;
import com.bumptech.glide.Glide;

import java.util.List;

import static com.android.mercadolibre.offlinemovies.data.remote.ApiConstants.IMAGE_API_PREFIX;


public class MyMovieRecyclerViewAdapter extends RecyclerView.Adapter<MyMovieRecyclerViewAdapter.ViewHolder> {

    private List<MovieEntity> mValues;
    Context ctx;

    public MyMovieRecyclerViewAdapter(Context context, List<MovieEntity> items) {
        mValues = items;
        ctx = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);

        // Cargar la imagen con la libreria Glide.
        Glide.with(ctx)
                .load(IMAGE_API_PREFIX + holder.mItem.getPosterPath()) // Donde esta la imagen.
                .into(holder.imageViewCover); // Donde se carga la imagen.
    }

    // Metodo para cargar peliculas

    public void setData(List<MovieEntity> movies) {
        this.mValues = movies;
        // se refresca la data.
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(mValues != null)
            return mValues.size();
        else return 0;
    }

    // Clase que gestiona un elemento de la lista.
    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView imageViewCover;
        public MovieEntity mItem;

        public ViewHolder(View view) {
            super(view);
            imageViewCover = view.findViewById(R.id.imageView_cover);
            mView = view;
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }
}