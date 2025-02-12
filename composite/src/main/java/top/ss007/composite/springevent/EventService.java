package top.ss007.composite.springevent;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import top.ss007.composite.springevent.event.FinishPoopEvent;
import top.ss007.composite.springevent.event.OpenDoorEvent;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@Service
public class EventService {
    private final ApplicationEventPublisher publisher;

    public void goHome() {
        publisher.publishEvent(new OpenDoorEvent("open door", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)));
    }

    public void poop() {
        publisher.publishEvent(new FinishPoopEvent("finish shit", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)));

    }
}
