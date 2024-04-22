package com.example.iot_lab2_20193470;
import android.content.Context;
import android.net.ConnectivityManager;

import android.net.NetworkInfo;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ContadorActivity extends AppCompatActivity {
    private TextView tvPrimeNumber;
    private Button buttonDescAsc;
    private TextView textDescAsc;
    private Button buttonPauseRest;
    public int currentIndex = 0;
    private boolean isPaused = false;
    private boolean isAscending = true;
    private NumPrimoAPI api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.contador_primos);

        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = manager.getActiveNetworkInfo();
        boolean tieneInternet = activeNetworkInfo != null && activeNetworkInfo.isConnected();

        Toast.makeText(this, "Estás en la vista de contador de primos", Toast.LENGTH_SHORT).show();

        buttonDescAsc = findViewById(R.id.buttonAscDesc);
        textDescAsc = findViewById(R.id.textView6);
        buttonPauseRest = findViewById(R.id.buttonPauseRest);

        buttonDescAsc.setText(isAscending ? "Descender" : "Ascender");
        buttonDescAsc.setOnClickListener( new View.OnClickListener()  {
            @Override
            public void onClick(View v) {
                updateButtonAndText();
            }
        });

        buttonPauseRest.setText(isPaused ? "Reiniciar" : "Pausar");
        buttonPauseRest.setOnClickListener( new View.OnClickListener()  {
            @Override
            public void onClick(View v) {
                updateButton();
            }
        });

        contarNumPrimo();

    }

    private void updateButtonAndText() {
        isAscending = !isAscending;
        buttonDescAsc.setText(isAscending ? R.string.descender : R.string.ascender);
        textDescAsc.setText(isAscending ? R.string.descendiendo : R.string.ascendiendo);
    }

    public void pausarCounter() {
        isPaused = true;
    }

    public void reiniciarCounter() {
        isPaused = false;
        currentIndex = 0;
        contarNumPrimo();
    }

    private void updateButton() {
        isPaused = !isPaused;
        if (isPaused) {
            reiniciarCounter();
            buttonPauseRest.setText(R.string.pausar);
        } else {
            pausarCounter();
            buttonPauseRest.setText(R.string.reiniciar);
        }
    }

    public void contarNumPrimo() {

        tvPrimeNumber = findViewById(R.id.textViewPrimo);
        NumPrimoAPI numeroPrimoApi = new Retrofit.Builder()
                .baseUrl("https://prime-number-api.onrender.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(numeroPrimoApi.class);

        numeroPrimoApi.getPrimeNumbers(999,1).enqueue(new Callback<List<NumPrimo>>() {
            @Override
            public void onResponse(@NonNull Call<List<NumPrimo>> call, @NonNull Response<List<NumPrimo>> response) {
                if(response.isSuccessful()){
                    List<NumPrimo> listaprimo = response.body();

        });


    }

}