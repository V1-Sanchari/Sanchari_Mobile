package com.tourism.sanchari;

import com.google.gson.JsonObject;

import okhttp3.*;

import java.io.IOException;

public class AddUser {
    OkHttpClient client = new OkHttpClient();

    public void insertUser(String uname, String pw) {
        MediaType JSON = MediaType.get("application/json; charset=utf-8");

        // Create request body

        String json = "{ \"uname\": \"" + uname + "\", \"password\": \"" + pw + "\" }";
        RequestBody body = RequestBody.create(json, JSON);


        // Replace this with your actual API URL from Render
        String url = "https://sanchariweb.onrender.com/loginentry";

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        // Send the request asynchronously
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                System.out.println("❌ Failed to send request: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    System.out.println("✅ User added: " + response.body().string());
                } else {
                    System.out.println("❌ Error code: " + response.code());
                }
            }
        });
    }
}
