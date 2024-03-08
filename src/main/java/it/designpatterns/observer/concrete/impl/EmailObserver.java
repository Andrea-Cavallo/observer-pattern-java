package it.designpatterns.observer.concrete.impl;

import it.designpatterns.observer.concrete.IObserver;
import it.designpatterns.observer.model.AbstractMessage;
import it.designpatterns.observer.model.EmailMessage;
import lombok.extern.slf4j.Slf4j;

/**
 * The EmailObserver class is an implementation of the IObserver interface that handles email messages.
 *
 * <p>
 * EmailObserver overrides the handleMessage method to log the details of an email message when received.
 */
@Slf4j
public class EmailObserver implements IObserver {

    @Override
    public void handleMessage(AbstractMessage message) {
        if (message instanceof EmailMessage emailMessage) {
            log.info( "Email message received -> Sender: {}, Recipient: {}, Subject: {}, Body: {}",
                    emailMessage.getSenderEmail(), emailMessage.getRecipientEmail(),
                    emailMessage.getSubject(), emailMessage.getBody() );
        }
    }
}