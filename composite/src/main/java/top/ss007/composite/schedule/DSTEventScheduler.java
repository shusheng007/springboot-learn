package top.ss007.composite.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;


@Slf4j
@Component
public class DSTEventScheduler {
    private static final List<ZoneId> TARGET_ZONES = Arrays.asList(
            ZoneId.of("America/New_York"),
            ZoneId.of("America/Chicago"),
            ZoneId.of("America/Denver"),
            ZoneId.of("America/Los_Angeles")
    );

    private final TaskScheduler taskScheduler;

    public DSTEventScheduler(TaskScheduler taskScheduler) {
        this.taskScheduler = taskScheduler;
    }

    @Scheduled(fixedRate = 1000 * 30)
    public void extendTime() {
        scheduleDSTEvents();
    }

    //    @PostConstruct
    public void scheduleDSTEvents() {
        Instant now = Instant.now();
        log.info("now is:{}", now.getEpochSecond());

//        for (ZoneId zone : TARGET_ZONES) {
//            ZoneRules rules = zone.getRules();
//            for (ZoneOffsetTransitionRule rule : rules.getTransitionRules()) {
//                int currentYear = Year.now().getValue();
//                for (int year = currentYear; year <= currentYear + 0; year++) {
//                    try {
//                        ZoneOffsetTransition transition = rule.createTransition(year);
//                        log.info("==DST==:\n{}\ntimeZone:{}\ninstant:{}\nisGap(夏令时):{}\nisOverlap:{}\noffsetBefore:{}\noffsetAfter:{}\nduration:{}",
//                                transition.toString(), zone, transition.getInstant(),
//                                transition.isGap(),transition.isOverlap(),
//                                transition.getOffsetBefore(),transition.getOffsetAfter(),
//                                transition.getDuration());
//
//                        if (transition.getInstant().isAfter(now)) {
//                            scheduleLog(transition.getInstant(), zone, transition.isGap());
//                        }
//                    } catch (DateTimeException e) {
//                        log.warn("can not calculate transition", e);
//                    }
//                }
//            }
//        }
        //0 0 1 ? 3 7L *  每年3月最后一个周日 1:00 执行

        List<Instant> instants = List.of(now.plusSeconds(10), now.plusSeconds(20), now.plusSeconds(30));
        for (Instant instant : instants) {
            taskScheduler.schedule(() -> log.info("current time1:{}", instant.getEpochSecond()), instant);
        }
//        taskScheduler.schedule(() -> log.info("current time2:{}", Instant.now().getEpochSecond()),new CronTrigger("0/20 * * * * ?", ZoneId.systemDefault()));

        log.info("schedule number:{}", );
    }

    private void scheduleLog(Instant transitionTime, ZoneId zone, boolean isStart) {
        taskScheduler.schedule(
                () -> log.info("DST {} for {} at {}",
                        isStart ? "start" : "end", zone, transitionTime),
                transitionTime
        );
    }
}
