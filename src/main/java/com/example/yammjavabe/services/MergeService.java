package com.example.yammjavabe.services;

import com.example.yammjavabe.entities.MergeSettings;
import com.example.yammjavabe.utils.CreateEmail;
import com.google.api.client.util.Base64;
import com.google.api.services.gmail.model.Draft;
import com.google.api.services.gmail.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Properties;

@Service
@RequestScope
public class MergeService {

    private final UserGmailService userGmailService;
    private final UserSpreadsheetService userSpreadsheetService;

    @Autowired
    public MergeService(UserGmailService userGmailService, UserSpreadsheetService userSpreadsheetService) {
        this.userGmailService = userGmailService;
        this.userSpreadsheetService = userSpreadsheetService;
    }

    public void sendMerge(MergeSettings mergeSettings) throws IOException, MessagingException {
        Draft draft = userGmailService.getDraftById(mergeSettings.getDraftId());

        String to = "oleksandr.demian@revevol.eu";
        String from = "me";
        String subject = "Test subject";
        String rawBody = draft.getMessage().getRaw();

        Message message = CreateEmail.create(to, from, subject, rawBody);
        userGmailService.sendEmail(message);
    }
}
