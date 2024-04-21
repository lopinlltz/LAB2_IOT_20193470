package com.example.iot_lab2_20193470;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BusquedaActivity extends AppCompatActivity {
    private TextView textViewTitle, textViewYear, textViewRated, textViewRuntime, textViewGenre, textViewDirector, textViewPlot;
    private LinearLayout linearLayoutRatings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.busqueda_form);

        Toast.makeText(this, "Estás en la vista de información de la película", Toast.LENGTH_SHORT).show();

        textViewTitle = findViewById(R.id.textView_title);
        textViewYear = findViewById(R.id.textView_year);
        textViewRated = findViewById(R.id.textView_rated);
        textViewRuntime = findViewById(R.id.textView_runtime);
        textViewGenre = findViewById(R.id.textView_genre);
        textViewDirector = findViewById(R.id.textView_director);
        textViewPlot = findViewById(R.id.textView_plot);
        linearLayoutRatings = findViewById(R.id.linear_layout_ratings);

        Button buttonAtras = findViewById(R.id.buttonReturn);
        buttonAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BusquedaActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        String imdbId = getIntent().getStringExtra("imdbId");
        getMovieDetails(imdbId);

    }
    private void getMovieDetails(String imdbId) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.omdbapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PeliculaAPI apiService = retrofit.create(PeliculaAPI.class);
        Call<Pelicula> call = apiService.getMovieDetails("bf81d461", imdbId);
        call.enqueue(new Callback<Pelicula>() {
            @Override
            public void onResponse(Call<Pelicula> call, Response<Pelicula> response) {
                if (response.isSuccessful()) {
                    Pelicula pelicula = response.body();
                    updateViews(pelicula);
                } else {
                    Toast.makeText(BusquedaActivity.this, "Error al obtener detalles de la película", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Pelicula> call, Throwable t) {
                Toast.makeText(BusquedaActivity.this, "Error de red: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateViews(Pelicula movie) {
        textViewTitle.setText(movie.getTitle());
        textViewYear.setText(movie.getYear());
        textViewRated.setText(movie.getRated());
        textViewRuntime.setText(movie.getRuntime());
        textViewGenre.setText(movie.getGenre());
        textViewDirector.setText(movie.getDirector());
        textViewPlot.setText(movie.getPlot());

        List<Rating> ratings = movie.getRatings();
        for (Rating rating : ratings) {
            TextView textViewRating = new TextView(this);
            textViewRating.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            textViewRating.setText(rating.getSource() + ": " + rating.getValue());
            linearLayoutRatings.addView(textViewRating);
        }
    }
}
