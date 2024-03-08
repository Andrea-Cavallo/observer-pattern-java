package it.designpatterns.observer.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public abstract class AbstractMessage {

    protected String id;
    protected LocalDateTime time;
    protected String body;

    protected abstract String getPlatform();

}
