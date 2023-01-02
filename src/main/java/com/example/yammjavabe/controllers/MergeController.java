package com.example.yammjavabe.controllers;

import com.example.yammjavabe.controllers.requests.MergeRequest;
import com.example.yammjavabe.services.UserGmailService;
import com.example.yammjavabe.services.UserSpreadsheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/merge")
public class MergeController {

    private final UserGmailService userGmailService;
    private final UserSpreadsheetService userSpreadsheetService;

    @Autowired
    public MergeController(UserGmailService userGmailService, UserSpreadsheetService userSpreadsheetService) {
        this.userGmailService = userGmailService;
        this.userSpreadsheetService = userSpreadsheetService;
    }

    @PostMapping
    public void sendMerge (@RequestBody MergeRequest mergeRequest) throws Exception {
        userGmailService.logDrafts(mergeRequest);
    }
}
