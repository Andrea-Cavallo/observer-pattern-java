package it.designpatterns.observer.client;

import it.designpatterns.observer.concrete.IObserver;
import it.designpatterns.observer.concrete.impl.EmailObserver;
import it.designpatterns.observer.concrete.impl.WhatsAppObserver;
import it.designpatterns.observer.publisher.PollingPublisher;

import java.util.concurrent.TimeUnit;

/**
 * The ClientExample class is an example class to demonstrate the usage of the PollingPublisher class and its observers.
 * It creates instances of EmailObserver and WhatsAppObserver, adds them as observers to the PollingPublisher, starts polling,
 * waits for 15 seconds, and then removes the observers and stops polling.
 */
public class ClientExample {


    public static final String EMAIL_OBSERVER = "emailObserver";
    public static final String WHATS_APP_OBSERVER = "whatsAppObserver";

    public static void main(String[] args) {
        PollingPublisher pollingThread = PollingPublisher.getInstance();

        IObserver emailObserver = new EmailObserver();
        IObserver whatsAppObserver = new WhatsAppObserver();

        assert pollingThread != null;
        pollingThread.addObserver( EMAIL_OBSERVER, emailObserver );
        pollingThread.addObserver( WHATS_APP_OBSERVER, whatsAppObserver );

        pollingThread.startPolling( 2, TimeUnit.SECONDS );


        try {
            Thread.sleep( 15000 );

            pollingThread.stopPolling();
            pollingThread.removeObserver( EMAIL_OBSERVER );
            pollingThread.removeObserver( WHATS_APP_OBSERVER );
        } catch (InterruptedException e) {
            throw new RuntimeException( e );
        }
    }
}
