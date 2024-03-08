package it.designpatterns.observer.utils;

import it.designpatterns.observer.model.AbstractMessage;
import it.designpatterns.observer.model.EmailMessage;
import it.designpatterns.observer.model.WhatsAppMessage;

import java.time.LocalDateTime;
import java.util.Random;

public class ObserverUtils {
    private static final Random random = new Random();

    private ObserverUtils() {
    }

    /**
     * Generates a random message of type AbstractMessage.
     *
     * @return a random instance of AbstractMessage
     */
    public static AbstractMessage generateRandomMessage() {
        if (random.nextBoolean()) {
            return createRandomWhatsAppMessage();
        } else {
            return createRandomEmailMessage();
        }
    }

    /**
     * Creates a random instance of WhatsAppMessage.
     *
     * @return a random instance of WhatsAppMessage
     */
    private static WhatsAppMessage createRandomWhatsAppMessage() {
        return new WhatsAppMessage( "+390000000", "+39111111", "Rispondi Si a questo numero se hai correttamente visualizzato", LocalDateTime.now() );
    }

    /**
     * Creates a random instance of EmailMessage.
     *
     * @return a random instance of EmailMessage
     */
    private static EmailMessage createRandomEmailMessage() {
        return new EmailMessage( "publisher@email.com", "observer@email.com", "Salve abbiamo una nuova notifica per lei, controlli il suo cellulare", LocalDateTime.now(), "Ciao!" );
    }
}
