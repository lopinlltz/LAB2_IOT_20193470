package com.example.iot_lab2_20193470;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
public interface PeliculaAPI {
    @GET("/")
    Call<Pelicula> getDetallePelicula(@Query("apikey") String apiKey, @Query("i") String imdbId);
}
