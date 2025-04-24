package com.tourism.sanchari;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class APIClient {

    public interface APIClientCallback {
        void onDataFetched(List<PlaceMain> placeList);
        void onError(String errorMessage);
    }

    public void fetchData(Context context, final APIClientCallback callback) {
        // Replace this with your actual API URL
        String url = "https://sanchariweb.onrender.com/placesMain";

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("APIClient", "Error fetching data", e);
                callback.onError("Error fetching data: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful() && response.body() != null) {
                    String responseBody = response.body().string();

                    try {
                        Gson gson = new Gson();
                        PlaceMain[] places = gson.fromJson(responseBody, PlaceMain[].class);

                        // Convert array to list
                        List<PlaceMain> placeList = Arrays.asList(places);

                        // Pass the data to the callback on success
                        callback.onDataFetched(placeList);
                    } catch (Exception e) {
                        callback.onError("Parsing error: " + e.getMessage());
                    }

                } else {
                    callback.onError("Failed to fetch data: " + response.message());
                }
            }
        });
    }
}
