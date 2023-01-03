package com.example.yammjavabe.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.List;
import java.util.Properties;

import com.google.api.services.gmail.model.Message;
import com.google.api.services.gmail.model.MessagePart;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class CreateEmail {

    public static Message create(
            String to,
            String from,
            String subject,
            String rawBody
    ) throws MessagingException, IOException {
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);

        MimeMessage email = new MimeMessage(session);

        email.setFrom(new InternetAddress(from));
        email.addRecipient(javax.mail.Message.RecipientType.TO,
                new InternetAddress(to));
        email.setSubject(subject);
        email.setText(rawBody);

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        email.writeTo(buffer);
        byte[] rawMessageBytes = buffer.toByteArray();
        String encodedEmail = Base64.getEncoder().encodeToString(rawMessageBytes);
        Message message = new Message();
        message.setRaw(encodedEmail);

        return message;
    }

    /**
     * Only supports text/plan for now
     */
    public static String getEmailBodyText (Message message) {
        List<MessagePart> parts = message.getPayload().getParts();
        StringBuilder mixContent = new StringBuilder();

        for (MessagePart part : parts) {
            if (part.getMimeType().equalsIgnoreCase("text/plain")) {
                try {
                    mixContent.append(new String(Base64.getUrlDecoder().decode(part.getBody().getData()), "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }/* else if (part.getMimeType().equalsIgnoreCase("text/html")) {
                try {
                    mixContent.append(new String(Base64.getUrlDecoder().decode(part.getBody().getData()), "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }*/
        }

        return mixContent.toString();
    }
}
