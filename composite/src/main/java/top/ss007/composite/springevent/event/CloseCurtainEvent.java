package top.ss007.composite.springevent.event;


import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public class CloseCurtainEvent {
    private final String event;
    private final String time;

}

