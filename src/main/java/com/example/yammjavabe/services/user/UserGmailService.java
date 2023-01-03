package com.example.yammjavabe.services.user;

import com.example.yammjavabe.utils.UserCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Draft;
import com.google.api.services.gmail.model.ListDraftsResponse;
import com.google.api.services.gmail.model.Message;

import java.io.IOException;

/**
 * UserGmailService is not a Spring service, because it has to be bind to the user token
 * ../request/GmailService is a request scope Spring service that inherits all functions from UserGmailService and can be used as part of Spring lifecycle
 * This is because in some cases we need to manually create token bind services (ex: merge runs on separate thread, and cannot access RequestScope components)
 */
public class UserGmailService {
    private static final String APPLICATION_NAME = "Google Sheets Example";
    private Gmail gmailService;

    public UserGmailService (String bearerToken) throws Exception {
        gmailService = new Gmail.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                JacksonFactory.getDefaultInstance(), UserCredential.getGoogleCredential(bearerToken))
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    public ListDraftsResponse getAllDrafts () throws IOException {
        return gmailService.users().drafts().list("me").execute();
    }

    public Draft getDraftById (String draftId) throws IOException {
        return gmailService.users().drafts().get("me", draftId).execute();
    }

    public Message sendEmail (Message message) throws IOException {
        return gmailService.users().messages().send("me", message).execute();
    }
}
