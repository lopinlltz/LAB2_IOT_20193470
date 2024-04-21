package com.example.iot_lab2_20193470;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.io.IOException;
import java.util.List;

public class NumPrimoViewModel extends ViewModel {
    private MutableLiveData<Integer> primeNumber = new MutableLiveData<>();
    private List<NumPrimo> primeNumbers;
    private int currentIndex = 0;
    private boolean isAscending = true;
    private boolean isPaused = false;

    public MutableLiveData<Integer> getPrimeNumber() {
        return primeNumber;
    }

    public void toggleOrder() {
        isAscending = !isAscending;
    }

    public boolean isAscending() {
        return isAscending;
    }

    public void fetchPrimeNumbers() {
        new Thread(() -> {
            try {
                NumPrimoAPIService apiService = new NumPrimoAPIService();
                primeNumbers = apiService.getPrimeNumbers();
                showPrimeNumbers();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }


    public void pausarCounter() {
        isPaused = true;
    }

    public void reiniciarCounter() {
        isPaused = false;
        currentIndex = 0;
        showPrimeNumbers();
    }

    private void showPrimeNumbers() {
        new Thread(() -> {
            for (int i = 0; i < primeNumbers.size(); i++) {
                int index = isAscending ? currentIndex : primeNumbers.size() - 1 - currentIndex;
                int primeNumberValue = primeNumbers.get(index).number;
                Log.d("PRIME_NUMBER", "Updating prime number: " + primeNumberValue);
                primeNumber.postValue(primeNumberValue);

                currentIndex++;
                if (isAscending && currentIndex >= primeNumbers.size()) {
                    currentIndex = 0;
                    isAscending = false;
                } else if (!isAscending && currentIndex >= primeNumbers.size()) {
                    currentIndex = primeNumbers.size() - 1;
                    isAscending = true;
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
