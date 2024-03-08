package it.designpatterns.observer.publisher;

import it.designpatterns.observer.concrete.IObserver;
import it.designpatterns.observer.model.AbstractMessage;
import it.designpatterns.observer.utils.ObserverUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * The PollingPublisher class is responsible for managing observers and
 * notifying them periodically with generated messages.
 *
 * <p>
 * PollingPublisher uses a thread pool executor to schedule polling at a fixed rate.
 * It maintains a map of observers, where each observer is mapped to a unique observer key.
 * When polling is started, it generates a random message and notifies all registered observers with the message.
 *
 * <p>
 * Example usage:
 * <pre>{@code
 * PollingPublisher publisher = PollingPublisher.getInstance();
 * publisher.addObserver(observerKey, observer);
 * publisher.startPolling(1, TimeUnit.SECONDS);
 * }</pre>
 */
@Slf4j
public class PollingPublisher {
    private static volatile PollingPublisher instance;
    private final ConcurrentHashMap<Object, IObserver> observers = new ConcurrentHashMap<>();
    private ScheduledExecutorService scheduler;

    private PollingPublisher() {
        scheduler = Executors.newScheduledThreadPool( 1 );
    }

    /**
     * Returns the singleton instance of the PollingPublisher class.
     *
     * <p>
     * This method ensures that only one instance of the PollingPublisher class is created.
     * If the instance does not exist, it is created using double-checked locking synchronization.
     *
     * @return the singleton instance of the PollingPublisher class.
     */
    public static PollingPublisher getInstance() {
        if (instance == null) {
            synchronized (PollingPublisher.class) {
                if (instance == null) {
                    try {
                        instance = new PollingPublisher();
                    } catch (Exception e) {
                        log.error("Can't create instance of  PollingPublisher", e);
                        return null;
                    }
                }
            }
        }
        return instance;
    }

    /**
     * Adds an observer to the collection of observers.
     *
     * @param observerKey the key representing the observer
     * @param observer    the observer to be added
     */
    public void addObserver(Object observerKey, IObserver observer) {
        observers.put( observerKey, observer );
    }

    /**
     * Removes an observer from the list of observers.
     *
     * @param observerKey the key of the observer to be removed
     */
    public void removeObserver(Object observerKey) {
        observers.remove( observerKey );
    }

    /**
     * Starts the polling process at regular intervals.
     *
     * @param period The time duration between consecutive polls.
     * @param unit   The unit of time for the polling period.
     */
    public void startPolling(long period, TimeUnit unit) {
        final Runnable poller = () -> {
            log.info( "Start Polling......." );
            AbstractMessage message = ObserverUtils.generateRandomMessage();
            notifyObservers( message );
        };
        scheduler.scheduleAtFixedRate( poller, 0, period, unit );
    }

    /**
     * Stops the polling process.
     * <p>
     * If the scheduler is not null, this method will shut down the scheduler, interrupt any running threads,
     * and create a new scheduler with a single thread.
     *
     * @throws InterruptedException if the current thread is interrupted while sleeping
     */
    public void stopPolling() throws InterruptedException {
        if (scheduler != null) {
            log.info(".......Stop Polling");
            scheduler.shutdown();
            if (!scheduler.awaitTermination(5, TimeUnit.SECONDS)) {
                log.warn("Timeout during  thread pool stop.");
            }
            scheduler = Executors.newScheduledThreadPool(1);
        }
    }


    /**
     * Notifies all the registered observers with the given message.
     *
     * @param msg the abstract message to be sent to the observers
     */
    private void notifyObservers(AbstractMessage msg) {
        for (IObserver observer : observers.values()) {
            observer.handleMessage( msg );
        }
    }
}
