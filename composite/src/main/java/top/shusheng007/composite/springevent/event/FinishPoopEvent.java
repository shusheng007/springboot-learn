package top.shusheng007.composite.springevent.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class FinishPoopEvent {
    private final String event;
    private final String time;
}
