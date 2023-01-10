import redis.clients.jedis.Jedis;
import util.JedisPoolUtil;

import java.util.Map;
import java.util.Set;

public class Test1 {


    public static void main(String[] args) {
        Map<String, String> redis_map = null;

        Jedis jedis = JedisPoolUtil.getJedisPoolInstance().getResource();
        Set<String> redis_hkeys = jedis.hkeys("Proto:Container");
        jedis.hget("Proto:Container", "ContainerID:CMAU4456251");
    }

}
