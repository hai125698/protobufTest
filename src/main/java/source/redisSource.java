package source;

import com.alibaba.fastjson.JSONObject;
import optim.Aliproto;
import org.apache.flink.streaming.api.functions.source.SourceFunction;
import redis.clients.jedis.Jedis;
import util.JedisPoolUtil;

import java.util.Map;
import java.util.Set;

public class redisSource implements SourceFunction<Aliproto.Container> {

    private boolean isRunning = true;
    private Jedis jedis;
    private Map<String, String> redis_map;

    @Override
    public void run(SourceContext<Aliproto.Container> sourceContext) throws Exception {
        //使用jedis 获取连接池, 并且获取数据
        jedis = JedisPoolUtil.getJedisPoolInstance().getResource();

        while (isRunning) {

            Set<String> redis_hkeys = jedis.hkeys("Proto:Container");

            for (String redisHkey : redis_hkeys) {
                JSONObject key_jsonObject = JSONObject.parseObject(redisHkey);

                redis_map.put(redisHkey, jedis.hget("Proto:Container", key_jsonObject.getString("ContainerID")));

            }
        }

    }

    @Override
    public void cancel() {

    }
}
