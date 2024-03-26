package top.ss007.composite.springevent.event;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class TurnOnLightsEvent {
    private final String event;
    private final String time;

}
