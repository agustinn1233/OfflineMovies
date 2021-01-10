package com.android.mercadolibre.offlinemovies.data.remote;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class RequestInterceptor implements Interceptor {

    @NotNull
    @Override
    public Response intercept(Chain chain) throws IOException {
        // Metodo que toma la solicitud que estamos tomando en el servidor y le concatenamos la API_KEY

        // Peticion que se quiere enviar al servidor
        Request originalRequest = chain.request();
        HttpUrl originalHttpUrl = originalRequest.url();

        HttpUrl url = originalHttpUrl.newBuilder()
                .addQueryParameter("api_key", ApiConstants.API_KEY)
                .build();

        // Request para invocar a la url.
        Request request = originalRequest.newBuilder()
                .url(url)
                .build();

        // Continua con el nuevo request que creamos.
        return chain.proceed(request);

    }
}
