package br.com.bruno.redis.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private UUID id;
    private Long customerId;
    private String description;

    public void newId() {
        this.id = UUID.randomUUID();
    }
    //private List<OrderItem> items;

}
