package top.shusheng007.redisintegrate.service;

import io.lettuce.core.RedisAsyncCommandsImpl;
import io.lettuce.core.RedisFuture;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisStringAsyncCommands;
import io.lettuce.core.api.reactive.RedisStringReactiveCommands;
import io.lettuce.core.api.sync.RedisStringCommands;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;
import top.shusheng007.redisintegrate.run.KeyValue;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by shusheng007
 *
 * @author benwang
 * @date 2022/10/13 11:21
 * @description:
 */

@Service
@Slf4j
@RequiredArgsConstructor
public class RedisOpsService {

    private final RedisTemplate<String, Object> redisTemplate;
    private final RedisScript<Boolean> moneyTransferScript;

    public void testValue() {
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        valueOperations.set("string", "I am a string");
        log.info("string:{}", valueOperations.get("string"));

        valueOperations.set("number", 1);
        log.info("init number:{}", valueOperations.get("number"));
        valueOperations.increment("number", 5);
        log.info("incr number:{}", valueOperations.get("number"));

        BoundValueOperations<String, Object> boundStr = redisTemplate.boundValueOps("boundStr");
        boundStr.set("My");
        log.info("bound str:{}", boundStr.get());


    }

    public void testHash() {
        HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();
        hashOperations.put("hash", "key1", "value1");
        log.info("hash key1-value:{}", hashOperations.get("hash", "key1"));

        BoundHashOperations<String, Object, Object> boundHash = redisTemplate.boundHashOps("boundHash");
        boundHash.put("bkey1", "value1");
        boundHash.put("bkey2", "value2");
        log.info("boudHash values:{}", boundHash.multiGet(Arrays.asList("bkey1", "bkey2")));
    }

    public void testList() {
        ListOperations<String, Object> listOperations = redisTemplate.opsForList();
        listOperations.leftPushAll("list", "l1", "l2", "l3");
        log.info("list :{}", listOperations.rightPop("list"));

        BoundListOperations<String, Object> boundList = redisTemplate.boundListOps("boundList");
        boundList.leftPushAll("l1", "l2", "l3");
        log.info("bound list :{}", boundList.rightPop(2));
    }

    public void testSet() {
        SetOperations<String, Object> setOperations = redisTemplate.opsForSet();
        setOperations.add("set", "I", "am", "am", "shusheng007");
        log.info("set:{}", setOperations.pop("set", 10));

        BoundSetOperations<String, Object> boundSet1 = redisTemplate.boundSetOps("boundSet1");
        boundSet1.add("set1", "set2", "set3");

        BoundSetOperations<String, Object> boundSet2 = redisTemplate.boundSetOps("boundSet2");
        boundSet2.add("set3", "set4", "set5");

        Set set1 = boundSet1.intersect(boundSet2.getKey());
        log.info("set intersect:{}", set1);

        Set set2 = boundSet1.diff(boundSet2.getKey());
        log.info("set diff:{}", set2);

        Set set3 = boundSet1.union(boundSet2.getKey());
        log.info("set union:{}", set3);
    }

    public void testZSet() {
        ZSetOperations<String, Object> zSetOperations = redisTemplate.opsForZSet();
        zSetOperations.add("zset1", "age", 20.0d);
        Set<ZSetOperations.TypedTuple<Object>> typedTuples = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            ZSetOperations.TypedTuple<Object> tuple = new DefaultTypedTuple<>("value" + i, i * 1.0d);
            typedTuples.add(tuple);
        }
        zSetOperations.add("zset1", typedTuples);
        log.info("zset:{}", zSetOperations.range("zset1", 0, 13));
    }

    public void testGeo() {
        GeoOperations<String, Object> geoOperations = redisTemplate.opsForGeo();

        List<RedisGeoCommands.GeoLocation<Object>> cityGeos = new ArrayList<>();
        cityGeos.add(new RedisGeoCommands.GeoLocation<>("北京", new Point(116.405285, 39.904989)));
        cityGeos.add(new RedisGeoCommands.GeoLocation<>("天津", new Point(117.190182, 39.125596)));
        cityGeos.add(new RedisGeoCommands.GeoLocation<>("上海", new Point(121.472644, 31.231706)));
        geoOperations.add("city", cityGeos);

        List<String> hashs = geoOperations.hash("city", "天津", "北京");
        log.info("GEO的hash值 :{}", hashs);

        Distance distance = geoOperations.distance("city", "北京", "天津", Metrics.KILOMETERS);
        log.info("北京到天津的距离为:{}", distance);

        GeoResults<RedisGeoCommands.GeoLocation<Object>> members = geoOperations.radius("city", "北京", new Distance(200, Metrics.KILOMETERS));
        log.info("北京200km内有哪些城市:{}", members.getContent());
    }

    public void testTransaction() {
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        valueOperations.set("trx_key", "watch");
        //包含事务中每条命令的执行结果
        List<Object> trxResult = redisTemplate.execute(new SessionCallback<List<Object>>() {
            @Override
            public List<Object> execute(RedisOperations operations) throws DataAccessException {
                //启动watch
                operations.watch("trx_key");
                operations.multi();
                operations.opsForValue().set("key1", "value1");
                Object value1 = operations.opsForValue().get("key1");
                log.info("key1的值为:{}", value1);
//                operations.opsForValue().increment("key1");
                operations.opsForValue().set("key2", "value2");

                //测试的时候在此处打断点，然后使用redis客户端修改watch的值后再执行，看看能否执行成功
                return operations.exec();
            }
        });
        log.info("事务执行结果:{}", trxResult);
    }

    public void testPipeline() {
        List<String> results = new ArrayList<>();

        List<Object> pipeResult = redisTemplate.executePipelined(new SessionCallback<Object>() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                for (int i = 0; i < 10; i++) {
                    operations.opsForValue().set("pip:key" + i, "value" + i);
                    results.add(String.valueOf(operations.opsForValue().get("pip:key" + i)));
                }
                log.info("命令只是进入队列，还没有执行, 结果:{}", results);
                return null;
            }
        });
        log.info("执行结果类型:{}，结果:{}", pipeResult.getClass(), pipeResult);
        log.info("读取数据结果:{}", results);
    }

    public Boolean testLuaScript() {
        BoundHashOperations<String, Object, Object> accounts = redisTemplate.boundHashOps("account");
        String from = "from";
        accounts.put(from, 250);
        String to = "to";
        accounts.put(to, 200);
        RedisSerializer<String> strSerializer = new StringRedisSerializer();
        RedisSerializer<Boolean> boolSerializer = new RedisSerializer<Boolean>() {
            @Override
            public byte[] serialize(Boolean aBoolean) throws SerializationException {
                return String.valueOf(aBoolean).getBytes(StandardCharsets.UTF_8);
            }

            @Override
            public Boolean deserialize(byte[] bytes) throws SerializationException {
                return Boolean.valueOf(new String(bytes, StandardCharsets.UTF_8));
            }
        };

        Boolean result = redisTemplate.execute(moneyTransferScript,
                strSerializer, boolSerializer,
                Arrays.stream(new String[]{from, to}).collect(Collectors.toList()),
                "50");
        log.info("转账结果:{}", result);
        return result;
    }


    public void testDistributeLock() {
        final String lockKey = "lock";
        final String lockValue = UUID.randomUUID().toString();
        try {
            Boolean isSuccess = redisTemplate.opsForValue().setIfAbsent(lockKey, lockValue, 30, TimeUnit.SECONDS);
            log.info("线程[{}]加锁状态:{}", Thread.currentThread().getName(), isSuccess);
            if (!isSuccess) {
                long startTime = System.currentTimeMillis();
                boolean isTrySuccess = false;
                //下面这一段代码是让线程自旋10秒，也就是说当线程加锁的时候发现锁被人占了，它就在10秒内不断的尝试，10秒后放弃
                while (true) {
                    long currentTime = System.currentTimeMillis();
                    if (currentTime - startTime > 10 * 1000) {
                        log.info("线程[{}]自旋加锁失败");
                        break;
                    }
                    Thread.sleep(1000);
                    isTrySuccess = redisTemplate.opsForValue().setIfAbsent(lockKey, lockValue, 30, TimeUnit.SECONDS);
                    log.info("线程[{}]自旋加锁状态:{}", Thread.currentThread().getName(), isTrySuccess);
                    if (isTrySuccess) {
                        break;
                    }
                }
                if (!isTrySuccess) {
                    return;
                }
            }
            //业务代码
            business();
        } catch (Exception e) {
            log.error("加锁异常", e);
        } finally {
            releaseLock(lockKey, lockValue);
        }
    }

    private void releaseLock(String lockKey, String lockValue) {
        //只有持有锁的线程才能释放锁，释放锁这两步，比较和删除需要原子性，应该使用Lua脚本实现
//        if (lockValue.equals(redisTemplate.opsForValue().get(lockKey))) {
//            log.info("线程[{}]释放锁:{}", Thread.currentThread().getName(), lockValue);
//            redisTemplate.delete(lockKey);
//        }

        String delLua = new StringBuilder()
                .append("if redis.call('get',KEYS[1]) == ARGV[1]")
                .append("\n")
                .append("then")
                .append("\n")
                .append(" return redis.call('del',KEYS[1])")
                .append("\n")
                .append("else")
                .append("\n")
                .append(" return 0")
                .append("\n")
                .append("end")
                .toString();
        log.info("删除的lua脚本:\n{}", delLua);
        RedisScript<Long> rs = RedisScript.of(delLua, Long.class);
//        RedisSerializer<String> argsSerializer = new StringRedisSerializer();
//        RedisSerializer<Long> resultSerializer = new GenericToStringSerializer<Long>(Long.class, StandardCharsets.UTF_8);
//        Long result = redisTemplate.execute(rs, argsSerializer, resultSerializer, Arrays.asList(lockKey), lockValue);

        //不传参数和返回值的序列化器则使用template的value的序列化器
        Long result = redisTemplate.execute(rs, Arrays.asList(lockKey), lockValue);
        if (result > 0) {
            log.info("线程[{}]释放锁:{}", Thread.currentThread().getName(), lockValue);
        }
    }

    private void business() {
        log.info("线程[{}]开始处理业务...", Thread.currentThread().getName());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("线程[{}]结束处理业务...", Thread.currentThread().getName());
    }

    public void testRedisClient() {
        Object nativeConnection = redisTemplate.getConnectionFactory().getConnection().getNativeConnection();
        RedisAsyncCommandsImpl redisAsyncCommands = null;
        if (nativeConnection instanceof RedisAsyncCommandsImpl) {
            redisAsyncCommands = (RedisAsyncCommandsImpl) nativeConnection;
        }
        if (Objects.isNull(redisAsyncCommands)) {
            return;
        }
        //顺利拿到了Lettuce的StatefulRedisConnection，接下来就可以使用Lettuce客户端的各种操作了
        StatefulRedisConnection<String, Object> lettuceCon = redisAsyncCommands.getStatefulConnection();

        //同步操作
        RedisStringCommands<String, Object> sync = lettuceCon.sync();
        sync.set("l:key1", "value1");
        log.info("lettuce sync get:{}", sync.get("l:key1"));

        //异步操作
        RedisStringAsyncCommands<String, Object> async = lettuceCon.async();
        RedisFuture<String> set = async.set("l:key2", "value2");
        RedisFuture<Object> get = async.get("l:key2");

        get.thenAccept(new Consumer<Object>() {
            @Override
            public void accept(Object o) {
                log.info("lettuce async get:{}", o);
            }
        });

        //Reactive用法
        RedisStringReactiveCommands<String, Object> reactive = lettuceCon.reactive();
        Mono<String> reactSet = reactive.set("l:key3", "value3");
        Mono<Object> reactGet = reactive.get("l:key3");

        reactGet.subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) {
                log.info("lettuce react get:{}", o);
            }
        });
    }


}
