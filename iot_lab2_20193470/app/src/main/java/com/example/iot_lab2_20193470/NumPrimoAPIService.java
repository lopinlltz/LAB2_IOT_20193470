package com.example.iot_lab2_20193470;
import android.util.Log;

import java.io.IOException;
import java.util.List;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class NumPrimoAPIService {
    private static final String API_URL = "https://prime-number-api.onrender.com/";
    private NumPrimoAPI api;

    public NumPrimoAPIService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(NumPrimoAPI.class);
    }

    public List<NumPrimo> getPrimeNumbers() throws IOException {
        Call<List<NumPrimo>> call = api.getPrimeNumbers(999, 1);
        //Response<List<NumPrimo>> response = call.execute();
        //return response.body();
        Response<List<NumPrimo>> response = call.execute();
        List<NumPrimo> primeNumbers = response.body();
        Log.d("API_RESPONSE", "Received prime numbers: " + primeNumbers);
        return primeNumbers;
    }
}
