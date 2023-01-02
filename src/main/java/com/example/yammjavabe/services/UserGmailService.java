package com.example.yammjavabe.services;

import com.example.yammjavabe.controllers.requests.MergeRequest;
import com.example.yammjavabe.utils.UserCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Draft;
import com.google.api.services.gmail.model.ListDraftsResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import java.io.IOException;

@Service
@RequestScope
public class UserGmailService {
    private static final String APPLICATION_NAME = "Google Sheets Example";
    private Gmail gmailService;

    @Autowired
    public UserGmailService (HttpServletRequest request) throws Exception {
        System.out.println("Create new UserGmailService");
        String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
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

    @Async
    public void logDrafts (MergeRequest mergeRequest) throws IOException {
        var drafts = getAllDrafts();

        for (var getDraft : drafts.getDrafts()) {
            Draft draft = getDraftById(getDraft.getId());
            System.out.println(mergeRequest.getUserEmail() + ": " + draft.getMessage().getSnippet());
        }
    }
}
