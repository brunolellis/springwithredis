package br.com.bruno.redis;

import br.com.bruno.redis.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;

@SpringBootApplication
public class OrderCacheableApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(OrderCacheableApplication.class, args);
    }

    @Autowired
    private OrderService service;

    @Override
    public void run(String... strings) throws Exception {
        UUID id = UUID.randomUUID();

        for (int i = 0; i < 10; i++) {
            System.out.println(i + " = " + service.findById(id));
        }


        System.out.println(" = " + service.findById(id));
        System.out.println(" = " + service.findById(id));
        System.out.println(" = " + service.findById(id));
        System.out.println(" = " + service.findById(id));

    }
}
