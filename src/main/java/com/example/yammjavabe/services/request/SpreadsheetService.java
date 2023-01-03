package com.example.yammjavabe.services.request;

import com.example.yammjavabe.services.user.UserSpreadsheetService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

/**
 * This is a Spring service that extends user token bound UserSpreadsheetService
 * This way we can use all the functions as both part of Spring lifecycle and standalone
 *
 * This service is RequestScope, this way it can be bound to the user token passed as header
 */
@Service
@RequestScope
public class SpreadsheetService extends UserSpreadsheetService {
    @Autowired
    public SpreadsheetService (HttpServletRequest request) throws Exception {
        super(request.getHeader(HttpHeaders.AUTHORIZATION));
    }
}
