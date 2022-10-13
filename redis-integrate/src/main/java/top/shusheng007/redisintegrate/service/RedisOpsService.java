package top.shusheng007.redisintegrate.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import top.shusheng007.redisintegrate.run.KeyValue;

import java.nio.charset.StandardCharsets;
import java.util.*;
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

//        redisTemplate.getConnectionFactory().getConnection().getNativeConnection()
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


}
