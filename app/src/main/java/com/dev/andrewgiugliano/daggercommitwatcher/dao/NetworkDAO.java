package com.dev.andrewgiugliano.daggercommitwatcher.dao;

import android.util.Log;

import com.dev.andrewgiugliano.daggercommitwatcher.exception.DaoException;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Data Access Object to execute a GET request given a URL that returns JSON, and parses into a Gson
 * Json Java Object
 *
 * @author Andrew Giugliano
 * @version 1.0
 */
public class NetworkDAO {

    private static final String GROUP_TAG = "Network DAO";

    /**
     * Executes URL request, and returns data.
     * @param url URL to execute GET request on
     * @return Json data response
     * @throws DaoException If there was an error executing request
     */
    public JsonElement executeGetRequest(URL url) throws DaoException {
        HttpURLConnection httpRequest = null;
        JsonElement jsonElement = null;

        try {
            httpRequest = (HttpURLConnection) url.openConnection();
            httpRequest.connect();
            InputStreamReader istreamReader = new InputStreamReader((InputStream) httpRequest.getContent());
            JsonParser jsonParser = new JsonParser();
            jsonElement = jsonParser.parse(istreamReader);
            istreamReader.close();
        } catch(IOException e) {
            String msg = String.format("Error occurred while making GET request to %s",
                    url.toString());
            Log.e(GROUP_TAG, msg, e);

            throw new DaoException(e);
        } finally {
            if(httpRequest != null){
                httpRequest.disconnect();
            }
        }

        return jsonElement;
    }
}
