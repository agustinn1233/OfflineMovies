package com.android.mercadolibre.offlinemovies.app;

import android.app.Application;
import android.content.Context;

public class MyApp extends Application {

    // Se define para obtener la instancia en el lugar que quieramos.
    private static MyApp instance;
    public static MyApp getInstance() { return  instance; }
    // Contexto espacio de memoria donde nos encontramos trabajando y donde se almacenan las variables/obj
    public static Context getContext() { return instance; }

    @Override
    public void onCreate() {
        instance = this;
        super.onCreate();
    }
}
