package com.example.iot_lab2_20193470;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ContadorActivity extends AppCompatActivity {
    private TextView tvPrimeNumber;
    private Button buttonDescAsc;
    private TextView textDescAsc;
    private Button buttonPauseRest;
    private boolean isPaused = false;
    private NumPrimoViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contador_primos);

        Toast.makeText(this, "Estás en la vista de contador de primos", Toast.LENGTH_SHORT).show();

        tvPrimeNumber = findViewById(R.id.textViewPrimo);
        buttonDescAsc = findViewById(R.id.buttonAscDesc);
        textDescAsc = findViewById(R.id.textView6);
        buttonPauseRest = findViewById(R.id.buttonPauseRest);

        viewModel = new ViewModelProvider(this).get(NumPrimoViewModel.class);

        viewModel.getPrimeNumber().observe(this, primeNumber -> {
            tvPrimeNumber.setText(String.valueOf(primeNumber));
        });

        buttonDescAsc.setOnClickListener( v -> {
            viewModel.toggleOrder();
            updateButtonAndText();
        });

        buttonPauseRest.setOnClickListener( v -> {
            if (isPaused) {
                viewModel.reiniciarCounter();
                buttonPauseRest.setText(R.string.pausar);
            } else {
                viewModel.pausarCounter();
                buttonPauseRest.setText(R.string.reiniciar);
            }
            isPaused = !isPaused;

        });

        viewModel.fetchPrimeNumbers();
    }
    private void updateButtonAndText() {
        buttonDescAsc.setText(viewModel.isAscending() ? R.string.descender : R.string.ascender);
        textDescAsc.setText(viewModel.isAscending() ? R.string.descendiendo : R.string.ascendiendo);
    }

    private void updateButton() {
        buttonDescAsc.setText(viewModel.isAscending() ? R.string.descender : R.string.ascender);
    }

}
