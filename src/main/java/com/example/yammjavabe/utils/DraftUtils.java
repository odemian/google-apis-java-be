package com.example.yammjavabe.utils;

import com.google.api.services.gmail.model.Draft;
import com.google.api.services.gmail.model.MessagePartHeader;

import java.util.List;

public class DraftUtils {
    public static String getSubject (Draft draft) {
        List<MessagePartHeader> headers = draft.getMessage().getPayload().getHeaders();
        for (MessagePartHeader header : headers) {
            if (header.getName().equalsIgnoreCase("Subject")) {
                return header.getValue();
            }
        }

        return null;
    }
}
