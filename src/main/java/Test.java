import com.google.protobuf.InvalidProtocolBufferException;
import redis.clients.jedis.Jedis;
import util.JedisPoolUtil;

import java.util.Map;
import java.util.Set;

public class Test {


    public static void main(String[] args) throws InvalidProtocolBufferException {
        Map<String, String> redis_map = null;

        Jedis jedis = JedisPoolUtil.getJedisPoolInstance().getResource();
        //ProtostuffSerializer serializer = new ProtostuffSerializer();
        String key = "Proto:Container";

        Set<String> hkeys = jedis.hkeys(key);
        //"ContainerID:GCXU5493978"
        byte[] bytes = jedis.hget("Proto:Container", "ContainerID:GCXU5493978").getBytes();


    }

}
