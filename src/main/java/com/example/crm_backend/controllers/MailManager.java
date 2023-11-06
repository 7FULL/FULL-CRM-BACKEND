package com.example.crm_backend.controllers;

import com.mailjet.client.ClientOptions;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.transactional.*;
import com.mailjet.client.transactional.response.SendEmailsResponse;
import org.springframework.stereotype.Component;

import java.beans.JavaBean;

/*
 *
 *@author Pablo Hermida Gómez DAM G1
 *
 */
@Component
public class MailManager {

    private final String mailJetKey = "6c15b25b5e8166204d2c388114d68101";
    private final String secretMailJetKey = "3baef8ff9b8cd415598284268992c1b3";


    ClientOptions options = ClientOptions.builder()
            .apiKey(mailJetKey)
            .apiSecretKey(secretMailJetKey)
            .build();

    MailjetClient client = new MailjetClient(options);

    public void sendEmail(String to, String nameTo, String subject, String text){
        TransactionalEmail message1 = TransactionalEmail
                .builder()
                .to(new SendContact(to, nameTo))
                .from(new SendContact("phermidagomez@gmail.com", "FULL"))
                .htmlPart(text)
                .subject(subject)
                .build();

        SendEmailsRequest request = SendEmailsRequest
                .builder()
                .message(message1)
                .build();


        try{
            SendEmailsResponse response = request.sendWith(client);

            System.out.println(response.toString());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
