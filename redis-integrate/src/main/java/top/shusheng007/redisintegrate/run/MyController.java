package top.shusheng007.redisintegrate.run;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import top.shusheng007.redisintegrate.service.RedisOpsService;

/**
 * Created by shusheng007
 *
 * @author benwang
 * @date 2022/10/7 12:08
 * @description:
 */
@RestController
@RequestMapping("/redis")
@RequiredArgsConstructor
public class MyController {
    private final RedisOpsService redisOpsService;

    @GetMapping("/testValue")
    public void testValue(){
        redisOpsService.testValue();
    }

    @GetMapping("/testHash")
    public void testHash(){
        redisOpsService.testHash();
    }

    @GetMapping("/testList")
    public void testList(){
        redisOpsService.testList();
    }

    @GetMapping("/testSet")
    public void testSet(){
        redisOpsService.testSet();
    }

    @GetMapping("/testZSet")
    public void testZSet(){
        redisOpsService.testZSet();
    }

    @GetMapping("/testGeo")
    public void testGeo(){
        redisOpsService.testGeo();
    }

    @GetMapping("/testTrx")
    public void testTransaction(){
        redisOpsService.testTransaction();
    }

    @GetMapping("/testPipeline")
    public void testPipeline(){
        redisOpsService.testPipeline();
    }

    @GetMapping("/testLua")
    public Boolean testLua(){
       return redisOpsService.testLuaScript();
    }

    @GetMapping("/testLock")
    public void testLock(){
        redisOpsService.testDistributeLock();
    }

    @GetMapping("/testClient")
    public void testClient(){
        redisOpsService.testRedisClient();
    }

}
