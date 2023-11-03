package top.shusheng007.composite.springevent;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;
import top.shusheng007.composite.springevent.event.CloseCurtainEvent;
import top.shusheng007.composite.springevent.event.FinishShitEvent;
import top.shusheng007.composite.springevent.event.OpenDoorEvent;
import top.shusheng007.composite.springevent.event.TurnOnLightsEvent;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Component
public class HomeEventListener {

    @EventListener
    public TurnOnLightsEvent listenOpenDoorEvent(OpenDoorEvent event) {
        log.info("{}: {}", event.getTime(), event.getEvent());
        return new TurnOnLightsEvent("turn on light", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    }

    @EventListener
    public CloseCurtainEvent listenTurnOnLightEvent(TurnOnLightsEvent event) {
        log.info("{}: {}", event.getTime(), event.getEvent());
        return new CloseCurtainEvent("close the curtain",
                LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    }

    @Async
    @EventListener
    public void listenCloseCurtainEvent(CloseCurtainEvent event) {
        log.info("{}: {}", event.getTime(), event.getEvent());
    }

    //use with Transactional
    @TransactionalEventListener
    public void listenShitEvent(FinishShitEvent event) {
        log.info("{}: {}", event.getTime(), event.getEvent());
    }


    @EventListener({ContextStartedEvent.class, ContextRefreshedEvent.class, ContextStoppedEvent.class})
    public void listenContextEvents(ApplicationContextEvent event) {
        log.info("context event received: {}", event.getClass().getSimpleName());
    }
}
