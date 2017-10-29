package com.dev.andrewgiugliano.daggercommitwatcher.util;

import android.net.Uri;
import android.util.Log;
import android.util.Pair;

import com.dev.andrewgiugliano.daggercommitwatcher.exception.UtilException;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Utility class for URL operations
 *
 * @author Andrew Giugliano
 * @version 1.0
 */
public class URLUtil {

    private static final String GROUP_TAG = "URL Utility";

    /**
     * Build a URL based on a base URL and a list of query parameters
     * @param baseUrl Base URL
     * @param paramList List of Parameters as Pairs where the left is the key, and the right is the value
     * @return URL object
     * @throws UtilException If there is an issue building the URL
     */
    public static URL buildUrl(String baseUrl, List<Pair<String, String>> paramList) throws UtilException {

        Uri.Builder uriBuilder = Uri.parse(baseUrl).buildUpon();
        for( Pair<String, String> param : paramList) {
            uriBuilder.appendQueryParameter(param.first, param.second);
        }

        URL url;

        try {
            url = new URL(uriBuilder.build().toString());
        } catch (MalformedURLException e) {
            Log.e(GROUP_TAG, "Error while building the URL object", e);
            throw new UtilException(e);
        }

        return url;
    }
}
