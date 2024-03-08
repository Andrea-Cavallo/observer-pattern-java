# Observer Design Pattern Implementation

This package provides an implementation of the Observer design pattern, specifically showcasing its use in the `PollingPublisher` class.

## What is the Observer Design Pattern?

The Observer pattern defines a one-to-many dependency between objects, where the objects are referred to as a "subject" and its "observers". When the subject's state changes, all its dependent observers are notified and updated automatically.

### Key Components:

- **Subject**: The entity that maintains a list of its dependents (observers) and provides methods for attaching and detaching them. It also has methods for notifying the observers of changes.
- **Observer**: The object that is interested in the subject's state. It defines an update interface for receiving notifications from the subject.

## The PollingPublisher Class

The `PollingPublisher` class serves as the subject in this implementation. It focuses on:

- **Managing Observers**: Enables the dynamic addition and removal of observers using the `addObserver` and `removeObserver` methods.
- **Generating Notifications**: Periodically generates messages using a background thread (scheduler).
- **Notifying Observers**: Iterates through its observers and calls their `handleMessage` method to deliver the generated messages.

## How to Use the PollingPublisher

### Get an Instance:

```java
PollingPublisher publisher = PollingPublisher.getInstance(); // Singleton pattern
```
