package com.alexbros.alexey.recyclerviewallgoals;

import java.net.HttpURLConnection;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.net.URL;
import java.io.IOException;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.net.MalformedURLException;

class HTTPConnection {

    private static String result = null;

    String GetHTTPData(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            result = stringBuilder.toString();

            urlConnection.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
