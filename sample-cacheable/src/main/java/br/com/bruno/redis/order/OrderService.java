package br.com.bruno.redis.order;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderService {

    @Cacheable(cacheNames = "ORDERS", cacheManager = "redisCacheManager", key = "#id.toString()")
    public Order findById(UUID id) {

        System.out.println("FINDBYID!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return Order.builder()
                .id(id)
                .customerId(System.currentTimeMillis())
                .description("order " + System.currentTimeMillis())
                .build();
    }


}
