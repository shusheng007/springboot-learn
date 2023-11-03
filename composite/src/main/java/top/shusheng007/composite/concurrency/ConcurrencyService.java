package top.shusheng007.composite.concurrency;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class ConcurrencyService {

    @Async("threadPoolExecutor")
    public void runAsync(Integer id) {
        log.info("start:{},num:{}", Thread.currentThread().getId(), id);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("end:{},num:{}", Thread.currentThread().getId(), id);
    }


    @Async
    public CompletableFuture<String> getFirstName() {
        log.info("start get first name");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return CompletableFuture.completedFuture("shusheng");
    }

    @Async
    public CompletableFuture<String> getLastName() {
        log.info("start get last name");
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return CompletableFuture.completedFuture("007");
    }


}
