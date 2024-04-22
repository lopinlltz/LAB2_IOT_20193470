package com.example.iot_lab2_20193470;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BusquedaActivity extends AppCompatActivity {
    private Button buttonAtras;
    private CheckBox checkBox;
    private TextView textViewTitle, textViewYear, textViewRated, textViewRuntime, textViewGenre, textViewDirector, textViewPlot;
    private TextView textViewIMDB, textViewRT, textViewMeta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.busqueda_form);

        Toast.makeText(this, "Estás en la vista de información de la película", Toast.LENGTH_SHORT).show();

        textViewTitle = findViewById(R.id.textViewTITLE);
        textViewYear = findViewById(R.id.textViewYEAR);
        textViewRated = findViewById(R.id.textViewRATED);
        textViewRuntime = findViewById(R.id.textViewRUNTIME);
        textViewGenre = findViewById(R.id.textViewGENRE);
        textViewDirector = findViewById(R.id.textViewDIRECTOR);
        textViewPlot = findViewById(R.id.textView_plot);
        textViewIMDB = findViewById(R.id.textViewIMDB);
        textViewRT = findViewById(R.id.textViewRT);
        textViewMeta = findViewById(R.id.textViewMETA);

        checkBox = findViewById(R.id.checkBox);
        buttonAtras = findViewById(R.id.buttonReturn);

        buttonAtras.setEnabled(false);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                buttonAtras.setEnabled(isChecked);
            }
        });

        buttonAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BusquedaActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
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

        Call<Pelicula> call = apiService.getDetallePelicula("bf81d461", imdbId);
        call.enqueue(new Callback<Pelicula>() {
            @Override
            public void onResponse(Call<Pelicula> call, Response<Pelicula> response) {
                if (response.isSuccessful()) {
                    Pelicula pelicula = response.body();

                    textViewTitle.setText(pelicula.getTitle());
                    textViewYear.setText(pelicula.getYear());
                    textViewRated.setText(pelicula.getRated());
                    textViewRuntime.setText(pelicula.getRuntime());
                    textViewGenre.setText(pelicula.getGenre());
                    textViewDirector.setText(pelicula.getDirector());
                    textViewPlot.setText(pelicula.getPlot());

                    if (pelicula.getRating() != null) {
                        for (Pelicula.Rating rating : pelicula.getRating()) {
                            switch (rating.getSource()) {
                                case "Internet Movie Database":
                                    textViewIMDB.setText(rating.getValue());
                                    break;
                                case "Rotten Tomatoes":
                                    textViewRT.setText(rating.getValue());
                                    break;
                                case "Metacritic":
                                    textViewMeta.setText(rating.getValue());

                            }
                        }
                    }

                    //textViewIMDB.setText(pelicula.getRating().get(0).getValue());
                    //textViewRT.setText(pelicula.getRating().get(1).getValue());
                    //textViewMeta.setText(pelicula.getRating().get(2).getValue());


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

}
