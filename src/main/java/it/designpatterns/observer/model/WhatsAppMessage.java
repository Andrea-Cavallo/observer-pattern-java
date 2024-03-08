package it.designpatterns.observer.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class WhatsAppMessage extends AbstractMessage {

    private String sender;
    private String recipient;

    public WhatsAppMessage(String sender, String recipient, String body, LocalDateTime time) {
        this.sender = sender;
        this.recipient = recipient;
        this.body = body;
        this.time = time;
    }

    @Override
    public String getPlatform() {
        return "WhatsApp";
    }
}