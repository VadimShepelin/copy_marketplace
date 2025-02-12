package com.spring.mail.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import org.hibernate.annotations.GenericGenerator;
import java.math.BigInteger;
import java.util.UUID;


@Entity
@Table(name = "order_items")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
public class OrderItems {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private UUID id;

    @Column(name = "order_id")
    private UUID orderId;

    @Column(name = "sku")
    private String sku;

    @Column(name = "quantity")
    private BigInteger quantity;
}
