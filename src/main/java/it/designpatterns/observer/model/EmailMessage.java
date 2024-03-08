package it.designpatterns.observer.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class EmailMessage extends AbstractMessage {

    private String senderEmail;
    private String recipientEmail;
    private String subject;

    public EmailMessage(String senderEmail, String recipientEmail, String body, LocalDateTime time, String subject) {
        this.senderEmail = senderEmail;
        this.recipientEmail = recipientEmail;
        this.body = body;
        this.time = time;
        this.subject = subject;
    }

    @Override
    public String getPlatform() {
        return "Email";
    }
}