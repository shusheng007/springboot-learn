package top.shusheng007.composite.springevent;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.*;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;
import top.shusheng007.composite.springevent.event.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
//@Order(1)
@Component
public class HomeEventListener {


    @Order(2)
    @EventListener(classes = {OpenDoorEvent.class, TurnOnLightsEvent.class},
            condition = "#object.event eq 'turn on light'")
    public void listenMultiEvent(Object object) {
        log.info("监听到了事件:{}", object);
    }

    @EventListener
    public TurnOnLightsEvent listenOpenDoorEvent(OpenDoorEvent event) {
        log.info("{}: {}", event.getTime(), event.getEvent());
        return new TurnOnLightsEvent("turn on light", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    }

    @Order(1)
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
    public void listenShitEvent(FinishPoopEvent event) {
        log.info("{}: {}", event.getTime(), event.getEvent());
    }

    @EventListener
    public void listenGenericEvent(GenericDailyEvent<FinishPoopEvent> event){
        log.info("{}: {}", event.getMyEvent().getTime(), event.getMyEvent().getEvent());
    }


    @EventListener({ContextStartedEvent.class, ContextRefreshedEvent.class, ContextStoppedEvent.class})
    public void listenContextEvents(ApplicationContextEvent event) {
        log.info("context event received: {}", event.getClass().getSimpleName());
    }
}
