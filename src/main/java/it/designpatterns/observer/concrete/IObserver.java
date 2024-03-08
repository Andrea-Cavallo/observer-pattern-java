package it.designpatterns.observer.concrete;

import it.designpatterns.observer.model.AbstractMessage;

/**
 * The IObserver interface represents an observer that can handle messages.
 *
 * <p>
 * Implementations of this interface should implement the {@code handleMessage} method to process incoming messages.
 */
public interface IObserver {

    void handleMessage(AbstractMessage message);

}
