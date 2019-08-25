package br.com.bruno.redis.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private RedisTemplate<String, Order> redisTemplate;

    public Order save(Order order) {
        if (order.getId() == null) {
            order.newId();
        }

        String key = key(order);
        redisTemplate.opsForValue().set(key, order, 5, TimeUnit.MINUTES);

        return order;
    }

    private String key(Order order) {
        return key(order.getId());
    }

    private String key(UUID id) {
        return "ORDER:" + id;
    }

    public Order findById(UUID id) {
        String key = key(id);
        return redisTemplate.opsForValue().get(key);
    }

    public List<Order> findAll(Collection<UUID> ids) {
        Collection<String> idsRedis = ids.stream()
                .map(id -> key(id))
                .collect(Collectors.toList());
        return redisTemplate.opsForValue().multiGet(idsRedis);
    }
}
