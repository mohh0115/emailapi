package com.email.controller;

import com.email.model.EmailRequest;
import com.email.model.EmailResponse;
import com.email.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class EmailController {

    @Autowired
    private EmailService emailService;

    @RequestMapping("/welcome")
    public String welcome() {
        return "This is my Email Api";
    }

    @PostMapping("/emailSend")
    public ResponseEntity<?> sendEmail(@RequestBody EmailRequest emailRequest) {
        boolean res = emailService.sendEmail(emailRequest.getMessage(), emailRequest.getSubject(), emailRequest.getTo());
        if (res) {
            return ResponseEntity.ok(new EmailResponse("Email sent successfully"));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new EmailResponse("Email not sent"));
        }
    }
}
