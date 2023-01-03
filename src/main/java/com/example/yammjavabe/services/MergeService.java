package com.example.yammjavabe.services;

import com.example.yammjavabe.entities.EmailBodyParts;
import com.example.yammjavabe.entities.MergeData;
import com.example.yammjavabe.entities.MergeSettings;
import com.example.yammjavabe.services.user.UserGmailService;
import com.example.yammjavabe.services.user.UserSpreadsheetService;
import com.example.yammjavabe.utils.CreateEmail;
import com.example.yammjavabe.utils.DraftUtils;
import com.example.yammjavabe.utils.MergeDataUtils;
import com.example.yammjavabe.utils.TemplateProcessingUtils;
import com.google.api.services.gmail.model.Draft;
import com.google.api.services.gmail.model.Message;
import com.google.api.services.sheets.v4.model.MatchedValueRange;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MergeService {
    @Async
    public void sendMerge(String bearerToken, MergeSettings mergeSettings) throws Exception {
        UserGmailService userGmailService = new UserGmailService(bearerToken);
        UserSpreadsheetService userSpreadsheetService = new UserSpreadsheetService(bearerToken);

        Draft draft = userGmailService.getDraftById(mergeSettings.getDraftId());

        String from = "me";
        String subject = DraftUtils.getSubject(draft);

        if (subject == null) {
            throw new Exception("Cannot find subject");
        }

        EmailBodyParts bodyParts = CreateEmail.getEmailBodyText(draft.getMessage());

        List<MatchedValueRange> ranges = userSpreadsheetService.getValues(mergeSettings.getSpreadsheetId(), mergeSettings.getSheetId(), new Integer[] { 0, 0 });
        MergeData mergeData = MergeDataUtils.createMergeData(ranges.get(0).getValueRange().getValues());
        Integer toIndex = mergeData.getHeaderIndex(mergeSettings.getEmailHeader());

        if (toIndex == null) {
            throw new Exception("Cannot find emails");
        }

        for (List<Object> data : mergeData.getData()) {
            String to = (String) data.get(toIndex);
            String processSubject = TemplateProcessingUtils.createTemplate(subject, mergeData.getHeaders(), data);
            String plainText = null;
            String htmlText = null;

            if (bodyParts.getPlain() != null) {
                plainText = TemplateProcessingUtils.createTemplate(bodyParts.getPlain(), mergeData.getHeaders(), data);
            }

            if (bodyParts.getHtml() != null) {
                htmlText = TemplateProcessingUtils.createTemplate(bodyParts.getHtml(), mergeData.getHeaders(), data);
            }


            Message message = CreateEmail.create(to, from, processSubject, htmlText, plainText);
            userGmailService.sendEmail(message);
        }
    }
}
