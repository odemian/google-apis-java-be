package com.example.yammjavabe.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.List;
import java.util.Properties;

import com.example.yammjavabe.entities.EmailBodyParts;
import com.google.api.services.gmail.model.Message;
import com.google.api.services.gmail.model.MessagePart;

import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class CreateEmail {

    public static Message create(
            String to,
            String from,
            String subject,
            String html,
            String text
    ) throws MessagingException, IOException {
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);
        Multipart multiPart = new MimeMultipart("alternative");

        if (html != null) {
            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText(text, "utf-8");
            multiPart.addBodyPart(textPart);
        }

        if (text != null) {
            MimeBodyPart htmlPart = new MimeBodyPart();
            htmlPart.setContent(html, "text/html; charset=utf-8");
            multiPart.addBodyPart(htmlPart);
        }

        MimeMessage email = new MimeMessage(session);
        email.setFrom(new InternetAddress(from));
        email.addRecipient(javax.mail.Message.RecipientType.TO,
                new InternetAddress(to));
        email.setSubject(subject);
        email.setContent(multiPart);

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
    public static EmailBodyParts getEmailBodyText (Message message) {
        List<MessagePart> parts = message.getPayload().getParts();
        EmailBodyParts bodyParts = new EmailBodyParts();

        for (MessagePart part : parts) {
            if (part.getMimeType().equalsIgnoreCase("text/plain")) {
                try {
                    bodyParts.setPlain(new String(Base64.getUrlDecoder().decode(part.getBody().getData()), "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            } else if (part.getMimeType().equalsIgnoreCase("text/html")) {
                try {
                    bodyParts.setHtml(new String(Base64.getUrlDecoder().decode(part.getBody().getData()), "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }

        return bodyParts;
    }
}
