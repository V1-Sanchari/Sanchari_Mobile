package com.tourism.sanchari;

import okhttp3.*;

import java.io.IOException;

public class AddSignUp {
    OkHttpClient client = new OkHttpClient();

    public void insertUser(String name,String uname, String pw,String cpw) {
        MediaType JSON = MediaType.get("application/json; charset=utf-8");

        // Create request body
        String json = "{ \"name\": \"" + name + "\", \"uname\": \"" + uname + "\",\"pw\": \""+pw+"\",\"cpw\": \""+cpw+"\"}";
        RequestBody body = RequestBody.create(json, JSON);

        // Replace this with your actual API URL from Render
        String url = "https://sanchariweb.onrender.com/signup";

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
