package top.ss007.composite.springevent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    @GetMapping("/home")
    public String arrivedHome() {
        log.info("start event");
        eventService.goHome();
        eventService.poop();
        log.info("end event");
        return "ok";
    }
}
