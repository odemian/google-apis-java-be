package com.example.yammjavabe.utils;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;

public class UserCredential {

    public static GoogleCredential getGoogleCredential (String bearerAuthHeader) throws Exception {
        return new GoogleCredential().setAccessToken(getAuthToken(bearerAuthHeader));
    }

    private static String getAuthToken(String authHeader) throws Exception {
        String token;

        //must be in format: Bearer <token>
        String[] values = authHeader.split(" ");
        token = values[1];

        return token;
    }
}
