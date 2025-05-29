package top.shusheng007.composite.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.shusheng007.composite.base64.Base64Req;
import top.shusheng007.composite.base64.Base64ToMultipartFile;
import top.shusheng007.composite.concurrency.ConcurrencyService;
import top.shusheng007.composite.file.FileService;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Created by shusheng007
 *
 * @author benwang
 * @date 2022/5/14 2:27
 * @description:
 */

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/composite")
public class CompositeController {

    private final FileService fileService;

    private final ConcurrencyService concurrencyService;

    @PostMapping(value = "/uploadFile")
    public Boolean uploadFile(@RequestBody Base64Req request) {
        String[] base64Array = request.getBase64().split(",");
        String dataUir, data;
        if (base64Array.length > 1) {
            dataUir = base64Array[0];
            data = base64Array[1];
        } else {
            //根据你base64代表的具体文件构建
            dataUir = "data:image/jpg;base64";
            data = base64Array[0];
        }
        MultipartFile multipartFile = new Base64ToMultipartFile(data, dataUir);
        fileService.uploadFile(multipartFile);
        return true;
    }

    @GetMapping(value = "/test-filter")
    public String testFilter(@RequestParam(name = "name", required = false) String name) {
        return String.valueOf(name);
    }

    @GetMapping("/hello3")
    public Map<String, Object> hello3() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("code", 0);
        result.put("message", "");
        result.put("data", "hello world");
        return result;

//        throw new NullPointerException("null");

    }

    @GetMapping("/run-async")
    public String runAsync(@RequestParam("count") Integer count) {
/*        List<Integer> collect = IntStream.rangeClosed(1, count).boxed().collect(Collectors.toList());

        for (int i : collect) {
            new Thread(() -> concurrencyService.runAsync(i)).start();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                log.error("error", e);
            }
        }*/

        getFullName();
        return "run in back";
    }


    //    @Async
    private void getFullName() {
        log.info("start get fullName");
        CompletableFuture<String> firstName = concurrencyService.getFirstName();
        CompletableFuture<String> lastName = concurrencyService.getLastName();
        CompletableFuture<Void> result = CompletableFuture.allOf(firstName, lastName);
        try {
            result.get();
//            String fullName = Stream.of(firstName, lastName)
//                    .map(CompletableFuture::join)
//                    .collect(Collectors.joining(" "));
            log.info("end get fullName: {}", firstName.get() + lastName.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }


    }


}
