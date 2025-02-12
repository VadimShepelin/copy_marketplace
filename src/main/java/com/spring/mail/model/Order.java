package com.spring.mail.model;

import com.spring.mail.model.enums.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.IdClass;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "orders")
@IdClass(OrderId.class)
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
public class Order {

    @Id
    @Column(name = "id")
    private UUID orderId;

    @Column(name = "total_cost")
    private BigDecimal totalCost;

    @JdbcType(PostgreSQLEnumJdbcType.class)
    @Column(name = "status")
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @Id
    @JoinColumn(name = "user_id")
    private User user;
}
