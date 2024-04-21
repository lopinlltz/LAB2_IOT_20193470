package com.example.iot_lab2_20193470;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
public interface NumPrimoAPI {
    @GET("primeNumbers")
    Call<List<NumPrimo>> getPrimeNumbers(@Query("len") int len, @Query("order") int order);
}
