package com.example.yammjavabe.controllers;

import com.example.yammjavabe.controllers.requests.MergeRequest;
import com.example.yammjavabe.entities.MergeSettings;
import com.example.yammjavabe.services.MergeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/merge")
public class MergeController {
    private final MergeService mergeService;

    @Autowired
    public MergeController(MergeService mergeService) {
        this.mergeService = mergeService;
    }

    @PostMapping
    public void sendMerge (@RequestHeader(HttpHeaders.AUTHORIZATION) String bearerToken, @RequestBody MergeRequest mergeRequest) throws Exception {
        MergeSettings settings = new MergeSettings();
        settings.setDraftId(mergeRequest.getDraftId());
        settings.setSheetId(mergeRequest.getSheetId());
        settings.setSpreadsheetId(mergeRequest.getSpreadsheetId());

        mergeService.sendMerge(bearerToken, settings);
    }
}
