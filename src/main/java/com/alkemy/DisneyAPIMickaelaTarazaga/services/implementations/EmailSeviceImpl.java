package com.alkemy.DisneyAPIMickaelaTarazaga.services.implementations;


import com.alkemy.DisneyAPIMickaelaTarazaga.services.EmailService;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmailSeviceImpl implements EmailService {

    // For environment variables usage
    @Autowired
    private Environment environment;

    @Value("${alkemy.disneyapi.email.sender}")
    private String emailSender;
    @Value("${alkemy.disneyapi.email.enabled}")
    private boolean enabled;

    @Override
    public void sendWelcomeEmailTo(String to) {

      
        // SendGrid API Key as environment variable
        String apiKey = environment.getProperty("EMAIL_API_KEY");

        // Defining parts of the email like sender, recipient, content and subject
        Email fromEmail = new Email(emailSender);
        Email toEmail = new Email(to);
        Content content = new Content(
                "text/plain",
                "Welcome to Disney API! A challenge from Alkemy Labs by Mickaela Tarazaga."
        );
        String subject = "Getting Started at Disney API";

        // Creating the Email with all its parts, a SendGrid Object with the API key and a Request
        Mail mail = new Mail(fromEmail, subject, toEmail, content);
        SendGrid sendGrid = new SendGrid(apiKey);
        Request request = new Request();

        // Defining request attributes and saving the response of that request
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());

            Response response = sendGrid.api(request);

            //Showing the response details in console
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());

        } catch (IOException e) {
            System.out.println("Error trying to send the email.");
        }
    }
}
