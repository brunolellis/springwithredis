package br.com.bruno.redis;

import br.com.bruno.redis.order.Order;
import br.com.bruno.redis.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class OrderRedisTemplateApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(OrderRedisTemplateApplication.class, args);
    }

    @Autowired
    private OrderService service;

    @Override
    public void run(String... strings) throws Exception {
        Order order = new Order();
        order.setCustomerId(1L);
        order.setDescription("teste order " + System.currentTimeMillis());
        System.out.println(service.save(order));
        System.out.println(service.findById(order.getId()));



        List<UUID> ids = Arrays.asList(
                UUID.fromString("29732727-393b-40ce-b858-883c599279f5"),
                UUID.fromString("0fbc863e-893e-4cd4-a59d-b176a3e5d340"),
                UUID.fromString("47493192-d1d1-40cf-a592-c757cac588e6")
        );
        System.out.println(service.findAll(ids));

    }
}
