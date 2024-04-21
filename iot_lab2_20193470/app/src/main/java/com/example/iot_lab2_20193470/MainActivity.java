package com.example.iot_lab2_20193470;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText editTextMovieCode;
    private Button buttonSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.vista_inicial);

        Button buttonVisualizar = findViewById(R.id.button);
        buttonVisualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ContadorActivity.class);
                startActivity(intent);
            }
        });

        editTextMovieCode = findViewById(R.id.editTextMessage);
        buttonSearch = findViewById(R.id.button2);
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String movieCode = editTextMovieCode.getText().toString();
                Intent intent = new Intent(MainActivity.this, BusquedaActivity.class);
                intent.putExtra("movieCode", movieCode);
                startActivity(intent);
            }
        });

        if (hayInternet()) {
            Toast.makeText(this, "Hay conexión a Internet", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error: Sin conexión a Internet", Toast.LENGTH_SHORT).show();
        }

    }
    private boolean hayInternet() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
        return capabilities != null && (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR));
    }
}