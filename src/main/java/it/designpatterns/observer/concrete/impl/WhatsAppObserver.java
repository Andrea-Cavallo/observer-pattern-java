package it.designpatterns.observer.concrete.impl;


import it.designpatterns.observer.concrete.IObserver;
import it.designpatterns.observer.model.AbstractMessage;
import it.designpatterns.observer.model.WhatsAppMessage;
import lombok.extern.slf4j.Slf4j;

/**
 * WhatsAppObserver is an implementation of the IObserver interface that handles WhatsApp messages.
 * It overrides the handleMessage method to log the details of a WhatsApp message when received.
 */
@Slf4j
public class WhatsAppObserver implements IObserver {

    @Override
    public void handleMessage(AbstractMessage message) {
        if (message instanceof WhatsAppMessage whatsAppMessage) {
            log.info( "Whatsapp message received -> Sender: {}, Recipient: {}, Body: {}",
                    whatsAppMessage.getSender(), whatsAppMessage.getRecipient(), whatsAppMessage.getBody() );
        }
    }
}